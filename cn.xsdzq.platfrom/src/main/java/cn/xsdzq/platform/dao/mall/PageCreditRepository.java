package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditEntity;

public interface PageCreditRepository extends PagingAndSortingRepository<CreditEntity, Long>{
	Page<CreditEntity> findByOrderByCategoryCodeDesc(Pageable pageable);
	
	Page<CreditEntity> findByCategoryCodeLikeOrderByCategoryCodeDesc(String code, Pageable pageable);
	int countByCategoryCodeLike(String code);
	
	Page<CreditEntity> findByCategoryNameLikeOrderByCategoryCodeDesc(String name, Pageable pageable);
	int countByCategoryNameLike(String name);
	
	Page<CreditEntity> findByCategoryNameLikeAndCategoryCodeLikeOrderByCategoryCodeDesc(String name, String code, Pageable pageable);
	int countByCategoryNameLikeAndCategoryCodeLike(String name, String code);
}
