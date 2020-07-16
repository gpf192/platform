package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyProductRepository;
import cn.xsdzq.platform.entity.lcj.ProductEntity;

@Service(value = "myProductServiceImpl")
@Transactional(readOnly = true)
public class MyProductServiceImpl implements MyProductService {
	@Autowired
	private MyProductRepository myProductRepository;
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myProductRepository.count();
	}

	@Override
	public List<ProductEntity> getAllProduct(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductEntity> pages = myProductRepository.findByOrderById(pageRequest);
		List<ProductEntity> infos = pages.getContent();
		return infos;
	}

}
