package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.InfoEntity;

public interface MyInfoRepository extends PagingAndSortingRepository<InfoEntity, Long> {

	List<InfoEntity> findInfoEntityByCategoryId(long categoryId);

	int countInfoEntityByCategoryId(long categoryId);

	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultOrderByWeightDescModifytimeDesc(long categoryId,
			String checked_result, Pageable pageable);

	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByOrderByModifytimeDesc(long categoryId, String username,
			Pageable pageable);
}
