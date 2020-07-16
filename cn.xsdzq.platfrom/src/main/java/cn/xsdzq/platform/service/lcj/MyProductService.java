package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.ProductEntity;

public interface MyProductService {
	int countAll();
	List<ProductEntity> getAllProduct(int pageNumber, int pageSize);
}
