package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;

public interface CreditImportTempRepository extends PagingAndSortingRepository<CreditImportTempEntity, Long> {
	Page<CreditImportTempEntity> findByOrderByBeginDateDesc(Pageable pageable);
	
	Page<CreditImportTempEntity> findByClientIdOrderByBeginDateDesc(String clientId, Pageable pageable);
	int countByClientId(String clientId);
	
}
