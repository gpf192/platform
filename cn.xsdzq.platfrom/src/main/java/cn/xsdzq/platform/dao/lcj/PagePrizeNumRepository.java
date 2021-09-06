package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeNumberEntity;

public interface PagePrizeNumRepository extends PagingAndSortingRepository<PrizeNumberEntity, Long> {
	Page<PrizeNumberEntity> findByOrderByNumberDesc(Pageable pageable);
		
	Page<PrizeNumberEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(String clientName,  String clientId, Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName,  String clientId);
	
	Page<PrizeNumberEntity> findByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, Pageable pageable);
	int countByUserEntity_clientNameLike(String clientName );
	
	Page<PrizeNumberEntity> findByUserEntity_clientIdLikeOrderByNumberDesc( String clientId, Pageable pageable);
	int countByUserEntity_clientIdLike( String clientId);
	
}
