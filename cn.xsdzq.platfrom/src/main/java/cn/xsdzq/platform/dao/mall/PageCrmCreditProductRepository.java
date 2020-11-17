package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CRMCreditProductViewEntity;

public interface PageCrmCreditProductRepository extends PagingAndSortingRepository<CRMCreditProductViewEntity, Long> {
	Page<CRMCreditProductViewEntity> findByOrderByProductCode(Pageable pageable);
}
