package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.ParamEntity;

public interface MyParamRepository extends PagingAndSortingRepository<ParamEntity, Long> {
	Page<ParamEntity> findByOrderById(Pageable pageable);
	
	Page<ParamEntity> findByCodeLikeOrderById(String code, Pageable pageable);
	int countByCodeLike(String code);
}
