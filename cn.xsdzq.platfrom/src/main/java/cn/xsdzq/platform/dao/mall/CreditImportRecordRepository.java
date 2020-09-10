package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditImportRecordEntity;

public interface CreditImportRecordRepository extends PagingAndSortingRepository<CreditImportRecordEntity, Long> {
	Page<CreditImportRecordEntity> findByOrderByBeginDateDesc(Pageable pageable);
	
	Page<CreditImportRecordEntity> findByClientIdOrderByBeginDateDesc(String clientId, Pageable pageable);
	int countByClientId(String clientId);
}
