package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.lcj.AwardEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;


public interface MyAwardResultRepository extends JpaRepository<AwardResultEntity, Long> {
	List<AwardResultEntity> findByAwardEntity(AwardEntity awardEntity);

}
