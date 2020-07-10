package cn.xsdzq.platform.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.DzhActivityRepository;
import cn.xsdzq.platform.entity.DzhActivityEntity;

@Service(value = "dzhActivityServiceImpl")
@Transactional(readOnly = true)
public class DzhActivityServiceImpl implements DzhActivityService{
	Logger logger = LogManager.getLogger(DzhActivityServiceImpl.class.getName());

	@Autowired
	private DzhActivityRepository dzhActivityRepository;

	@Override
	public List<DzhActivityEntity> findByOrderByName() {
		// TODO Auto-generated method stub
		return dzhActivityRepository.findByOrderByName();
	}

	@Override
	public DzhActivityEntity findByName(String name) {
		// TODO Auto-generated method stub
		return dzhActivityRepository.findByName(name);
	}
}
