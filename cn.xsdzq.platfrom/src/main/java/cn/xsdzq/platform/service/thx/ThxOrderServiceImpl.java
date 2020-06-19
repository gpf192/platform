package cn.xsdzq.platform.service.thx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.thx.ThxOrderRepository;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

@Service(value = "thxOrderServiceImpl")
@Transactional(readOnly = true)
public class ThxOrderServiceImpl implements ThxOrderService{
	Logger logger = LogManager.getLogger(ThxOrderServiceImpl.class.getName());
	
	@Autowired
	private ThxOrderRepository thxOrderRepository;


	@Override
	@Transactional
	public void addInfo(ThxOrderEntity entity) {
		// TODO Auto-generated method stub
		thxOrderRepository.addInfo(entity);
	}



	@Override
	@Transactional
	public void modifyInfo(ThxOrderEntity entity) {
		// TODO Auto-generated method stub
		thxOrderRepository.modifyInfo(entity);
	}
}
