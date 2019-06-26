package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyProductSellRepository;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

@Service(value = "myProductSellServiceImpl")
@Transactional(readOnly = true)
public class MyProductSellServiceImpl implements MyProductSellService{
	@Autowired
	private MyProductSellRepository myProductSellRepository;
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myProductSellRepository.count();
	}

	@Override
	public List<ProductSellEntity> getAllProductSell(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByOrderById(pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}
}
