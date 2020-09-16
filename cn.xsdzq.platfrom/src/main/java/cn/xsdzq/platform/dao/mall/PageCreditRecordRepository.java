package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;

public interface PageCreditRecordRepository extends PagingAndSortingRepository<CreditRecordEntity, Long> {
	Page<CreditRecordEntity> findByOrderByBeginDateDesc(Pageable pageable);
	
	Page<CreditRecordEntity> findByMallUserEntity_ClientIdOrderByBeginDateDesc(String clientId, Pageable pageable);
	int countByMallUserEntity_ClientId(String clientId);
}
