package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.LcjPrizeNumberEntity;

public interface LcjPrizeNumRepository extends PagingAndSortingRepository<LcjPrizeNumberEntity, Long> {
	Page<LcjPrizeNumberEntity> findByOrderByNumberDesc(Pageable pageable);
		
	Page<LcjPrizeNumberEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(String clientName,  String clientId, Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName,  String clientId);
	
	Page<LcjPrizeNumberEntity> findByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, Pageable pageable);
	int countByUserEntity_clientNameLike(String clientName );
	
	Page<LcjPrizeNumberEntity> findByUserEntity_clientIdLikeOrderByNumberDesc( String clientId, Pageable pageable);
	int countByUserEntity_clientIdLike( String clientId);
	
}
