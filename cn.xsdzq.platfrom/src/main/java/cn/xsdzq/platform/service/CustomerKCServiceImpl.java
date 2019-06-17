package cn.xsdzq.platform.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.KCRepository;
import cn.xsdzq.platform.entity.CustomerMobileEntity;

@Service(value = "customerKCServiceImpl")
@Transactional(readOnly = true)
public class CustomerKCServiceImpl implements CustomerKCService{
	Logger logger = LogManager.getLogger(CustomerKCServiceImpl.class.getName());
	@Autowired
	private KCRepository KCRepository;

	@Override
	@Transactional
	public void addKCInfo(CustomerMobileEntity customerMobileEntity) {
		// TODO Auto-generated method stub
		KCRepository.addInfo(customerMobileEntity);
	}
}
