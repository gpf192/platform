package cn.xsdzq.platform.dao.thx;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.thx.UserRiskEntity;

public interface UserRiskRepository extends PagingAndSortingRepository<UserRiskEntity, Long> {
	Page<UserRiskEntity> findByOrderByEvaluationTimeDesc(Pageable pageable);
	
	Page<UserRiskEntity> findByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(Date endDate, Date beginDate, Pageable pageable);
	int countByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqual(Date endDate, Date beginDate);
	
	Page<UserRiskEntity> findByEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(Date beginDate, Pageable pageRequest);
	int countByEvaluationTimeGreaterThanEqual(Date beginDate);
	
	Page<UserRiskEntity> findByEvaluationTimeLessThanEqualOrderByEvaluationTimeDesc(Date endDate, Pageable pageRequest);
	int countByEvaluationTimeLessThanEqual(Date endDate);

}
