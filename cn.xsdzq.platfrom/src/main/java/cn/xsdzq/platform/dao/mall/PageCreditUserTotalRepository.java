package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditUserTotalEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;

public interface PageCreditUserTotalRepository extends PagingAndSortingRepository<CreditUserTotalEntity, Long> {
	Page<CreditUserTotalEntity> findByOrderByTotalDesc(Pageable pageable);
	
	Page<CreditUserTotalEntity> findByClientIdOrderByTotalDesc(String clientId, Pageable pageable);
	int countByClientId(String clientId);
}
