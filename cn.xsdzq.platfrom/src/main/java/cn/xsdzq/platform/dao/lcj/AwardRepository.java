package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.AwardEntity;

@Repository
public interface AwardRepository {
	AwardEntity getAwardById(long id);
	List<AwardEntity> getAllAward();
	void deleteAward(AwardEntity entity);

	void addAward(AwardEntity entity);

	void modifyAward(AwardEntity entity);
}
