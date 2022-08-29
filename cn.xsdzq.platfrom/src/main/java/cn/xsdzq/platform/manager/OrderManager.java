package cn.xsdzq.platform.manager;

import cn.xsdzq.platform.constants.OrderStatusEnum;
import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.MallUserInfoRepository;
import cn.xsdzq.platform.dao.mall.OrderRepository;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MallOrderEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.service.mall.MallUserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Component
public class OrderManager {
    @Resource
    private MallUserInfoRepository mallUserInfoRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private CreditRecordRepository creditRecordRepository;
    @Resource
    private MallUserService mallUserService;

    @Transactional
    public void update(MallOrderEntity updateOrder, MallUserInfoEntity updateUserInfo, CreditRecordEntity creditRecord) {
        int res;
        if (OrderStatusEnum.SUCCESS.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");

            res = mallUserInfoRepository.reduceUsableIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少可用积分失败");

            // 增加积分兑换记录
            creditRecordRepository.save(creditRecord);
            // 设置原始积分已被使用
            mallUserService.handleRudeceCredit(creditRecord.getMallUserEntity(), updateUserInfo.getFrozenIntegral());
        } else if (OrderStatusEnum.FAILURE.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");
        }

        orderRepository.save(updateOrder);
    }
}
