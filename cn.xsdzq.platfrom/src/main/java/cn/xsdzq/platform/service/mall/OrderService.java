package cn.xsdzq.platform.service.mall;

import cn.xsdzq.platform.entity.mall.MallOrderEntity;
import cn.xsdzq.platform.model.mall.OrderQueryDTO;
import cn.xsdzq.platform.model.mall.OrderSaveDTO;
import org.springframework.data.domain.Page;

public interface OrderService {

    void syncOrderStatus();

    void save(OrderSaveDTO orderSaveDTO);

    Page<MallOrderEntity> queryByPage(OrderQueryDTO orderQueryDTO);

}
