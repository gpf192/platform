package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.ProductRepository;
import cn.xsdzq.platform.entity.lcj.ProductEntity;

@Service(value = "productServiceImpl")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
	Logger logger = LogManager.getLogger(ProductServiceImpl.class.getName());
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductEntity> getAllProduct() {
		// TODO Auto-generated method stub
		List<ProductEntity> infos = productRepository.getAllProduct();
		return infos;
	}

	@Override
	public void addProduct(ProductEntity entity) {
		// TODO Auto-generated method stub
		productRepository.addProduct(entity);
	}

	@Override
	public void deleteProduct(ProductEntity entity) {
		// TODO Auto-generated method stub
		productRepository.deleteProduct(entity);
	}

	@Override
	public void modifyProduct(ProductEntity entity) {
		// TODO Auto-generated method stub
		productRepository.modifyProduct(entity);
	}

}
