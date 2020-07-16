package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import cn.xsdzq.platform.entity.lcj.AwardResultEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultViewEntity;

public interface AwardResultService {
	int countAll();	
	List<AwardResultViewEntity> getAllAwardResult(int pageNumber, int pageSize);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date beginDate, Date endDate, String prizeName, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String prizeName, String ClientId);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqual(Date beginDate);
	
	List<AwardResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqual(Date beginDate);
	
	List<AwardResultViewEntity> findByPrizeCodeOrderByRecordTime( String prizeName, int pageNumber, int pageSize);
	int countByPrizeCode(String prizeName);
	
	List<AwardResultViewEntity> findByClientIdOrderByRecordTime( String ClientId, int pageNumber, int pageSize);
	int countByClientId(String ClientId);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(Date beginDate, Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate,  String prizeName,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String prizeName);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(Date beginDate,  String ClientId,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId);
	
	List<AwardResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime( Date endDate, String prizeName, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeCode( Date endDate, String prizeName);
	
	List<AwardResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date endDate, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId);
	
	List<AwardResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTime(String prizeName, String ClientId, int pageNumber, int pageSize);
	int countByPrizeCodeAndClientId (String prizeName, String ClientId);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate, Date endDate, String prizeName, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate, String prizeName);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date beginDate,  String prizeName, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate,  String prizeName, String ClientId);
	
	List<AwardResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date endDate,  String prizeName, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date endDate,  String prizeName, String ClientId);
	
	List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date beginDate, Date endDate, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate, Date endDate, String ClientId);
	
}
