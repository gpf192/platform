package cn.xsdzq.platform.manager;

import cn.xsdzq.platform.constants.OrderStatusEnum;
import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.MallUserInfoRepository;
import cn.xsdzq.platform.dao.mall.OrderRepository;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MallOrderEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
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

    @Transactional
    public void update(MallOrderEntity updateOrder, MallUserInfoEntity updateUserInfo, CreditRecordEntity creditRecord) {
        int res;
        if (OrderStatusEnum.SUCCESS.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");

            res = mallUserInfoRepository.reduceUsableIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少可用积分失败");

            creditRecordRepository.save(creditRecord);
        } else if (OrderStatusEnum.FAILURE.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");

            res = mallUserInfoRepository.addUsableIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("增加可用积分失败");
        }

        orderRepository.save(updateOrder);
    }
}
