package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CardImportTempEntity;

public interface PageCardImportTempRepository extends PagingAndSortingRepository<CardImportTempEntity, Long> {
	Page<CardImportTempEntity> findByOrderByCardId(Pageable pageable);
	
	
}
