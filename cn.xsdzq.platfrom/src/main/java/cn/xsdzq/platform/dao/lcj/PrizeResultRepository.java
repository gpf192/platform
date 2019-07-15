package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.LcjUserEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;

public interface PrizeResultRepository extends  PagingAndSortingRepository<PrizeResultEntity, Long> {
	
	List<PrizeResultEntity> findByUserEntity(LcjUserEntity userEntity);
	//获取所有用户的获奖记录
	//List<PrizeResultEntity> findBy();
	Page<PrizeResultEntity> findByUserEntity_clientId(String clientId ,Pageable pageable);
	

}
