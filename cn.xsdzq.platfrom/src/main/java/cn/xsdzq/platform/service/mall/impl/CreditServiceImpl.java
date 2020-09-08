package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.CreditCategoryRepository;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.service.mall.CreditService;

@Service(value = "creditServiceImpl")
@Transactional(readOnly = true)
public class CreditServiceImpl implements CreditService{
	private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

	@Autowired
	private CreditCategoryRepository creditCategoryRepository;

	@Override
	@Transactional
	public void addCredit(CreditEntity entity) {
		// TODO Auto-generated method stub
		creditCategoryRepository.save(entity);
	}

	@Override
	public List<CreditEntity> getCreditEntities() {
		// TODO Auto-generated method stub
		return creditCategoryRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteCredit(CreditEntity entity) {
		// TODO Auto-generated method stub
		creditCategoryRepository.delete(entity);
	}

}
