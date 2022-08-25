package cn.xsdzq.platform.service.mall.impl;

import cn.xsdzq.platform.constants.ChengQuanOrderStatusEnum;
import cn.xsdzq.platform.constants.CreditRecordConst;
import cn.xsdzq.platform.constants.OrderStatusEnum;
import cn.xsdzq.platform.dao.mall.MallUserRepository;
import cn.xsdzq.platform.dao.mall.OrderRepository;
import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MallOrderEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.manager.OrderManager;
import cn.xsdzq.platform.service.lcj.ParamService;
import cn.xsdzq.platform.service.mall.OrderService;
import cn.xsdzq.platform.service.mall.client.chengquan.CommonRespEntity;
import cn.xsdzq.platform.service.mall.client.chengquan.DirectChargeService;
import cn.xsdzq.platform.service.mall.client.chengquan.GetOrderReqEntity;
import cn.xsdzq.platform.service.mall.client.chengquan.GetOrderRespEntity;
import cn.xsdzq.platform.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private ParamService paramService;
    @Resource
    private DirectChargeService directChargeService;
    @Resource
    private OrderManager orderManager;
    @Resource
    private MallUserRepository mallUserRepository;

    @Override
    public void syncOrderStatus() {
        PageRequest pageable = PageRequest.of(0, 20);

        ParamEntity startOffSetMinute = paramService.getValueByCode("orderScanStartOffSetMinute");
        ParamEntity endOffSetMinute = paramService.getValueByCode("orderScanEndOffSetMinute");
        Date now = new Date();
        Date scanStartTime = DateUtil.addMinute(now, Integer.valueOf(startOffSetMinute.getValue()));
        Date scanEndTime = DateUtil.addMinute(now, Integer.valueOf(endOffSetMinute.getValue()));

        ArrayList<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(OrderStatusEnum.PROCESSING.getCode());
        orderStatusList.add(OrderStatusEnum.INIT.getCode());

        Page<MallOrderEntity> page = orderRepository.findByCreateTimeGreaterThanAndCreateTimeLessThanAndOrderStatusInOrderByIdAsc(scanStartTime, scanEndTime, orderStatusList, pageable);
        List<MallOrderEntity> orderList = page.getContent();
        if (!CollectionUtils.isEmpty(orderList)) {
            for (MallOrderEntity order : orderList) {
                try {
                    process(order);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void process(MallOrderEntity order) {
        GetOrderReqEntity orderReq = new GetOrderReqEntity();
        orderReq.setOrder_no(order.getOrderNo());
        CommonRespEntity<GetOrderRespEntity> commonResp = directChargeService.getOrder(orderReq);
        MallOrderEntity updateOrder = new MallOrderEntity();
        BeanUtils.copyProperties(order, updateOrder);
        updateOrder.setRechargeCode(commonResp.getCode());
        updateOrder.setRechargeMessage(commonResp.getMessage());
        updateOrder.setUpdateTime(null);
        // 请求通过
        if (commonResp.getCode() == 7000) {
            GetOrderRespEntity data = commonResp.getData();
            updateOrder.setOrderStatus(getOrderStatus(data.getState()));
            updateOrder.setStartTime(DateUtil.strToDate(data.getStart_time()));
            updateOrder.setEndTime(DateUtil.strToDate(data.getEnd_time()));
            updateOrder.setRechargeStatus(data.getState());
            updateOrder.setConsumeAmount(data.getConsume_amount());
            // 系统异常
        } else if (commonResp.getCode() == 7777) {
            updateOrder.setOrderStatus(OrderStatusEnum.PROCESSING.getCode());
            // 失败
        } else {
            updateOrder.setOrderStatus(OrderStatusEnum.FAILURE.getCode());
        }

        if (!order.getOrderStatus().equals(updateOrder.getOrderStatus())) {
            MallUserInfoEntity updateUserInfo = new MallUserInfoEntity();
            updateUserInfo.setClientId(order.getClientId());
            updateUserInfo.setFrozenIntegral(order.getUseIntegral());

            CreditRecordEntity creditRecord = buildCreditRecord(updateOrder);
            orderManager.update(updateOrder, updateUserInfo, creditRecord);
        }
    }

    private Integer getOrderStatus(String chengQuanOrderStatus) {
        Integer orderStatus;
        switch (ChengQuanOrderStatusEnum.match(chengQuanOrderStatus)) {
            case SUCCESS:
                orderStatus = OrderStatusEnum.SUCCESS.getCode();
                break;
            case FAILURE:
                orderStatus = OrderStatusEnum.FAILURE.getCode();
                break;
            case RECHARGE:
            default:
                orderStatus = OrderStatusEnum.PROCESSING.getCode();
        }

        return orderStatus;
    }

    private CreditRecordEntity buildCreditRecord(MallOrderEntity order) {
        MallUserEntity user = mallUserRepository.findByClientId(order.getClientId());
        CreditRecordEntity credit = new CreditRecordEntity();
        credit.setType(CreditRecordConst.REDUCESCORE);
        credit.setReasonCode(CreditRecordConst.EXCHANGECARD);
        credit.setReason(CreditRecordConst.EXCHANGECARDREASON);
        credit.setItem(order.getGoodsName());
        credit.setIntegralNumber(order.getUseIntegral());
        String tradeDate = order.getTradeDate().toString();
        credit.setDateFlag(tradeDate.substring(0, 4) + "-" + tradeDate.substring(4, 6) + "-" + tradeDate.substring(6, 8));
        credit.setGroupTime(tradeDate.substring(0, 4) + "-" + tradeDate.substring(4, 6));
        credit.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
        credit.setRemindNumer(0);
        credit.setRecordTime(order.getEndTime());
        credit.setMallUserEntity(user);
        return credit;
    }

}
