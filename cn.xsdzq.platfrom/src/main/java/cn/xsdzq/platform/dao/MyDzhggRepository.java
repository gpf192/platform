package cn.xsdzq.platform.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.DzhggEntity;

public interface MyDzhggRepository extends PagingAndSortingRepository<DzhggEntity, Long> {
	Page<DzhggEntity> findByOrderByRecordtimeDesc(Pageable pageable);
}
