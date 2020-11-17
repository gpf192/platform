package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;

public interface PagePresentCategoryRepository extends  PagingAndSortingRepository<PresentCategoryEntity, Long> {
	Page<PresentCategoryEntity> findByNameNotOrderByCreatetimeDesc(String all, Pageable pageable);
	int countByNameNot(String all);
}
