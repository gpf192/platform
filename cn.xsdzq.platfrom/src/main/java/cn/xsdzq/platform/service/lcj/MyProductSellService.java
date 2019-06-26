package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

public interface MyProductSellService {
	int countAll();
	List<ProductSellEntity> getAllProductSell(int pageNumber, int pageSize);
}
