package cn.xsdzq.platform.service.lcj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.ParamRepository;
import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.service.InfoServiceImpl;

@Service(value = "paramServiceImpl")
@Transactional(readOnly = true)
public class ParamServiceImpl implements ParamService{
	Logger logger = LogManager.getLogger(InfoServiceImpl.class.getName());
	@Autowired
	private ParamRepository paramRepository;
	@Override
	public ParamEntity getValueByCode(String code) {
		// TODO Auto-generated method stub
		ParamEntity entity = paramRepository.getValueByCode(code);
		return entity;
	}
	@Override
	@Transactional
	public void deleteInfo(ParamEntity entity) {
		// TODO Auto-generated method stub
		paramRepository.deleteInfo(entity);
	}
	@Override
	@Transactional
	public void addInfo(ParamEntity entity) {
		// TODO Auto-generated method stub
		paramRepository.addInfo(entity);
	}
	@Override
	@Transactional
	public void modifyInfo(ParamEntity entity) {
		// TODO Auto-generated method stub
		paramRepository.modifyInfo(entity);
	}

}
