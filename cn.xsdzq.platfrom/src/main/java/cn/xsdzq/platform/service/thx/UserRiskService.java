package cn.xsdzq.platform.service.thx;

import java.util.Date;
import java.util.List;

import cn.xsdzq.platform.entity.thx.UserRiskEntity;

public interface UserRiskService {
	int countAll();
	List<UserRiskEntity> getAllUserRisk(int pageNumber, int pageSize);
	
	List<UserRiskEntity> findByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(Date endDate, Date beginDate,  int pageNumber, int pageSize);
	int countByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqual(Date endDate, Date beginDate); 
	
	List<UserRiskEntity> findByEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(Date beginDate,  int pageNumber, int pageSize);
	int countByEvaluationTimeGreaterThanEqual(Date beginDate);
	
	List<UserRiskEntity> findByEvaluationTimeLessThanEqualOrderByEvaluationTimeDesc(Date endDate, int pageNumber, int pageSize);
	int countByEvaluationTimeLessThanEqual(Date endDate);
}
