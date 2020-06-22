package cn.xsdzq.platform.service.thx;

import java.util.List;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

public interface UserOrderService {
	int countAll();
	List<ThxOrderEntity> getAllProduct(int pageNumber, int pageSize);
}
