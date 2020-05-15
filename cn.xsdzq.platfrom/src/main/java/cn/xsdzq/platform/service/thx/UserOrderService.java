package cn.xsdzq.platform.service.thx;

import java.util.List;

import cn.xsdzq.platform.entity.thx.UserOrderEntity;

public interface UserOrderService {
	int countAll();
	List<UserOrderEntity> getAllProduct(int pageNumber, int pageSize);
}
