package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.InfoEntity;

public interface MyInfoRepository extends PagingAndSortingRepository<InfoEntity, Long> {

	List<InfoEntity> findInfoEntityByCategoryId(long categoryId);

	int countInfoEntityByCategoryId(long categoryId);

	@Modifying(clearAutomatically = true)
	@Query("update InfoEntity i set i.pageView = i.pageView + 1 where i.id=?1")
	@Transactional
	void addPageView(Long id);

	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultOrderByWeightDescModifytimeDesc(long categoryId,
			String checked_result, Pageable pageable);

	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByOrderByModifytimeDesc(long categoryId, String username,
			Pageable pageable);
}
