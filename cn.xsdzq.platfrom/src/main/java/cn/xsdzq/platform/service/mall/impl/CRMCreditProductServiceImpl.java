package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PageCrmCreditProductRepository;
import cn.xsdzq.platform.entity.mall.CRMCreditProductViewEntity;
import cn.xsdzq.platform.service.mall.CRMCreditProductService;

@Service(value = "crmCreditProductServiceImpl")
@Transactional(readOnly = true)
public class CRMCreditProductServiceImpl implements CRMCreditProductService{
	@Autowired
	private PageCrmCreditProductRepository pageCrmCreditProductRepository;
	
	@Override
	public List<CRMCreditProductViewEntity> findByOrderByProductCode(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditProductViewEntity> pages = pageCrmCreditProductRepository.findByOrderByProductCode(pageRequest);		
		List<CRMCreditProductViewEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pageCrmCreditProductRepository.count();
	}

}
