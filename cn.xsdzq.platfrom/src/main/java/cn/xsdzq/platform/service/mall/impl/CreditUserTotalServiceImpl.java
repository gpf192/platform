package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PageCreditUserTotalRepository;
import cn.xsdzq.platform.entity.mall.CreditUserTotalEntity;
import cn.xsdzq.platform.service.mall.CreditUserTotalService;

@Service(value = "creditUserTotalServiceImpl")
@Transactional(readOnly = true)
public class CreditUserTotalServiceImpl implements CreditUserTotalService{
	private static final Logger logger = LoggerFactory.getLogger(CreditUserTotalServiceImpl.class);
	@Autowired
	private PageCreditUserTotalRepository pageCreditUserTotalRepository;
	
	@Override
	public List<CreditUserTotalEntity> findByOrderByTotalDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditUserTotalEntity> pages = pageCreditUserTotalRepository.findByOrderByTotalDesc(pageRequest);
			
		List<CreditUserTotalEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pageCreditUserTotalRepository.count();
	}

	@Override
	public List<CreditUserTotalEntity> findByClientIdOrderByTotalDesc(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditUserTotalEntity> pages = pageCreditUserTotalRepository.findByClientIdOrderByTotalDesc(clientId,pageRequest);
			
		List<CreditUserTotalEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return pageCreditUserTotalRepository.countByClientId(clientId);
	}

	@Override
	public CreditUserTotalEntity findByClientId(String clientId) {
		// TODO Auto-generated method stub
		CreditUserTotalEntity entity = pageCreditUserTotalRepository.findByClientId(clientId);
		return entity;
	}

	@Override
	@Transactional
	public void addEntity(CreditUserTotalEntity entity) {
		// TODO Auto-generated method stub
		pageCreditUserTotalRepository.save(entity);
	}
}
