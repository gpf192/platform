package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.AwardRepository;
import cn.xsdzq.platform.dao.lcj.MyAwardResultRepository;
import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.entity.lcj.AwardEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;

@Service(value = "awardServiceImpl")
@Transactional(readOnly = true)
public class AwardServiceImpl implements AwardService{
	Logger logger = LogManager.getLogger(AwardServiceImpl.class.getName());
	@Autowired
	private AwardRepository awardRepository;
	
	@Autowired
	private MyAwardResultRepository myAwardResultRepository;
	
	@Override
	public List<AwardEntity> getAllAward() {
		// TODO Auto-generated method stub
		List<AwardEntity> infos = awardRepository.getAllAward();
		return infos;
	}
	@Override
	@Transactional
	public void addAward(AwardEntity entity) {
		// TODO Auto-generated method stub
		awardRepository.addAward(entity);
	}
	@Override
	@Transactional
	public void deleteAward(AwardEntity entity) {
		// TODO Auto-generated method stub
		awardRepository.deleteAward(entity);
	}
	@Override
	@Transactional
	public void modifyAward(AwardEntity entity) {
		// TODO Auto-generated method stub
		AwardEntity info = awardRepository.getAwardById(entity.getId());
		info.setAwardName(entity.getAwardName());
		info.setAwardNameAlias(entity.getAwardNameAlias());
		info.setAwardValue(entity.getAwardValue());
		info.setImageName(entity.getImageName());
		info.setImageNumber(entity.getImageNumber());
		info.setIndex(entity.getIndex());
		awardRepository.modifyAward(info);
	}
	
	@Override
	public int getAwardResultNumber(AwardEntity awardEntity) {
		int total = 0;
		List<AwardResultEntity> awardResultEntities = myAwardResultRepository.findByAwardEntity(awardEntity);
		for (AwardResultEntity awardResultEntity : awardResultEntities) {
			total += awardResultEntity.getAwardNumber();
		}
		return total;
	}
}
