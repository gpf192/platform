package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.ProductEntity;

public interface ProductService {
	List<ProductEntity> getAllProduct();
	void addProduct(ProductEntity entity);

	void deleteProduct(ProductEntity entity);

	void modifyProduct(ProductEntity entity);
}
