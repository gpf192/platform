package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.ProductEntity;

@Repository
public interface ProductRepository {
	List<ProductEntity> getAllProduct();
	void deleteProduct(ProductEntity entity);

	void addProduct(ProductEntity entity);

	void modifyProduct(ProductEntity entity);
}
