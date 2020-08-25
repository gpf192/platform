package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import cn.xsdzq.platform.entity.lcj.LcjPrizeResultViewEntity;

public interface LcjPrizeResultService {


	int countAll();	
	List<LcjPrizeResultViewEntity> getAllPrizeRecord(int pageNumber, int pageSize);
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date beginDate, Date endDate, String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String PrizeCode, String ClientId);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqual(Date beginDate);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqual(Date beginDate);
	
	List<LcjPrizeResultViewEntity> findByPrizeCodeOrderByRecordTime( String PrizeCode, int pageNumber, int pageSize);
	int countByPrizeCode(String PrizeCode);
	
	List<LcjPrizeResultViewEntity> findByClientIdOrderByRecordTime( String ClientId, int pageNumber, int pageSize);
	int countByClientId(String ClientId);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(Date beginDate, Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate,  String PrizeCode,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String PrizeCode);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(Date beginDate,  String ClientId,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime( Date endDate, String PrizeCode, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeCode( Date endDate, String PrizeCode);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date endDate, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId);
	
	List<LcjPrizeResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTime(String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByPrizeCodeAndClientId (String PrizeCode, String ClientId);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate, Date endDate, String PrizeCode, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate, String PrizeCode);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date beginDate,  String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate,  String PrizeCode, String ClientId);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date endDate,  String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date endDate,  String PrizeCode, String ClientId);
	
	List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date beginDate, Date endDate, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate, Date endDate, String ClientId);
}
