package cn.xsdzq.platform.service.mall.impl;

import cn.xsdzq.platform.constants.AdjustmentTypeEnum;
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
import cn.xsdzq.platform.model.mall.OrderQueryDTO;
import cn.xsdzq.platform.model.mall.OrderSaveDTO;
import cn.xsdzq.platform.service.lcj.ParamService;
import cn.xsdzq.platform.service.mall.OrderService;
import cn.xsdzq.platform.service.mall.client.chengquan.CommonRespEntity;
import cn.xsdzq.platform.service.mall.client.chengquan.DirectChargeService;
import cn.xsdzq.platform.service.mall.client.chengquan.GetOrderReqEntity;
import cn.xsdzq.platform.service.mall.client.chengquan.GetOrderRespEntity;
import cn.xsdzq.platform.util.BeanHelper;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.UserManageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void save(OrderSaveDTO orderSaveDTO) {
        Optional<MallOrderEntity> optional = orderRepository.findById(orderSaveDTO.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException("更新数据不存在");
        } else {
            MallOrderEntity mallOrderEntity = optional.get();
            if (!AdjustmentTypeEnum.DEFAULT.getCode().equals(mallOrderEntity.getAdjustmentType())) {
                throw new RuntimeException("调账不允许");
            }

            if(AdjustmentTypeEnum.DEDUCTION.getCode().equals(orderSaveDTO.getAdjustmentType())){
                mallOrderEntity.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
                mallOrderEntity.setRechargeStatus(ChengQuanOrderStatusEnum.SUCCESS.getCode());
            }else if(AdjustmentTypeEnum.RETURN.getCode().equals(orderSaveDTO.getAdjustmentType())){
                mallOrderEntity.setOrderStatus(OrderStatusEnum.FAILURE.getCode());
                mallOrderEntity.setRechargeStatus(ChengQuanOrderStatusEnum.FAILURE.getCode());
            }else{
                throw new RuntimeException("调账类型不正确");
            }

            mallOrderEntity.setOperator(UserManageUtil.getUserName());
            BeanHelper.copyPropertiesIgnoreNull(orderSaveDTO, mallOrderEntity);

            MallUserInfoEntity updateUserInfo = new MallUserInfoEntity();
            updateUserInfo.setClientId(mallOrderEntity.getClientId());
            updateUserInfo.setFrozenIntegral(mallOrderEntity.getUseIntegral());

            CreditRecordEntity creditRecord = buildCreditRecord(mallOrderEntity);
            orderManager.update(mallOrderEntity,updateUserInfo, creditRecord);
        }
    }

    @Override
    public Page<MallOrderEntity> queryByPage(OrderQueryDTO orderQueryDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(orderQueryDTO.getPageNumber(), orderQueryDTO.getPageSize(), sort);

        MallOrderEntity order = new MallOrderEntity();
        BeanUtils.copyProperties(orderQueryDTO, order);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("pageNumber", "pageSize")
                .withMatcher("clientId", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("orderNo", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("orderStatus", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("rechargeStatus", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("adjustmentType", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("clientName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MallOrderEntity> example = Example.of(order, matcher);
        return orderRepository.findAll(example, pageable);
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
            updateOrder.setRechargeStatus(ChengQuanOrderStatusEnum.RECHARGE.getCode());
            // 失败
        } else {
            updateOrder.setOrderStatus(OrderStatusEnum.FAILURE.getCode());
            updateOrder.setRechargeStatus(ChengQuanOrderStatusEnum.FAILURE.getCode());
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
