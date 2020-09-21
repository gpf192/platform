package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditEntity;

public interface PageCreditRepository extends PagingAndSortingRepository<CreditEntity, Long>{
	Page<CreditEntity> findByCategoryNameNotOrderByCategoryCodeDesc(String all,Pageable pageable);
	int countByCategoryNameNot(String all);
	
	Page<CreditEntity> findByCategoryCodeLikeAndCategoryNameNotOrderByCategoryCodeDesc(String code,String all, Pageable pageable);
	int countByCategoryCodeLikeAndCategoryNameNot(String code,String all);
	
	Page<CreditEntity> findByCategoryNameLikeAndCategoryNameNotOrderByCategoryCodeDesc(String name,String all, Pageable pageable);
	int countByCategoryNameLikeAndCategoryNameNot(String name,String all);
	
	Page<CreditEntity> findByCategoryNameLikeAndCategoryCodeLikeOrderByCategoryCodeDesc(String name, String code,String all, Pageable pageable);
	int countByCategoryNameLikeAndCategoryCodeLikeAndCategoryNameNot(String name, String code,String all);
}
