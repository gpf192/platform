package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.LcjPrizeRecordEntity;

public interface LcjPrizeRecordRepository extends PagingAndSortingRepository<LcjPrizeRecordEntity, Long> {
	Page<LcjPrizeRecordEntity> findByTypeOrderByRecordTimeDesc(boolean type, Pageable pageable);
	int countByType(boolean type);
	
	Page<LcjPrizeRecordEntity> findByTypeAndUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByRecordTimeDesc(boolean type,String clientName,  String clientId, Pageable pageable);
	int countByTypeAndUserEntity_clientNameLikeAndUserEntity_clientIdLike(boolean type,String clientName,  String clientId);
	
	Page<LcjPrizeRecordEntity> findByTypeAndUserEntity_clientNameLikeOrderByRecordTimeDesc(boolean type,String clientName, Pageable pageable);
	int countByTypeAndUserEntity_clientNameLike(boolean type,String clientName );
	
	Page<LcjPrizeRecordEntity> findByTypeAndUserEntity_clientIdLikeOrderByRecordTimeDesc(boolean type, String clientId, Pageable pageable);
	int countByTypeAndUserEntity_clientIdLike(boolean type, String clientId);
}
