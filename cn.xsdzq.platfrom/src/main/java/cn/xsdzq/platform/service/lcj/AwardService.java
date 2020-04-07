package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.entity.lcj.AwardEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;

public interface AwardService {
	List<AwardEntity> getAllAward();
	void addAward(AwardEntity entity);

	void deleteAward(AwardEntity entity);

	void modifyAward(AwardEntity entity);
	int getAwardResultNumber(AwardEntity awardEntity);
	

}
