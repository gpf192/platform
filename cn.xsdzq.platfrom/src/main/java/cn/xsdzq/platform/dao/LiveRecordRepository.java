package cn.xsdzq.platform.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.LiveRecordEntity;

public interface LiveRecordRepository extends PagingAndSortingRepository<LiveRecordEntity, Long> {
	Page<LiveRecordEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<LiveRecordEntity> findByUserEntity_clientIdOrderByRecordTimeDesc(String ClientId, Pageable pageable);
	int countByUserEntity_clientId(String ClientId);
	
}
