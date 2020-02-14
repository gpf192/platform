package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;

public interface PrizeRecordService {
	

	int countAll();	
	List<PrizeResultEntity> getAllPrizeRecord(int pageNumber, int pageSize);
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(Date beginDate, Date endDate, String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate, Date endDate, String PrizeEntity_name, String UserEntity_clientId);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqual(Date beginDate);
	
	List<PrizeResultEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqual(Date beginDate);
	
	List<PrizeResultEntity> findByPrizeEntity_nameOrderByRecordTime( String PrizeEntity_name, int pageNumber, int pageSize);
	int countByPrizeEntity_name(String PrizeEntity_name);
	
	List<PrizeResultEntity> findByUserEntity_clientIdOrderByRecordTime( String UserEntity_clientId, int pageNumber, int pageSize);
	int countByUserEntity_clientId(String UserEntity_clientId);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(Date beginDate, Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameOrderByRecordTime(Date beginDate,  String PrizeEntity_name,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeEntity_name(Date beginDate, String PrizeEntity_name);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate,  String UserEntity_clientId,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId);
	
	List<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime( Date endDate, String PrizeEntity_name, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeEntity_name( Date endDate, String PrizeEntity_name);
	
	List<PrizeResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date endDate, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId);
	
	List<PrizeResultEntity> findByPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByPrizeEntity_nameAndUserEntity_clientId (String PrizeEntity_name, String UserEntity_clientId);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime(Date beginDate, Date endDate, String PrizeEntity_name, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_name(Date beginDate, Date endDate, String PrizeEntity_name);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(Date beginDate,  String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate,  String PrizeEntity_name, String UserEntity_clientId);
	
	List<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(Date endDate,  String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date endDate,  String PrizeEntity_name, String UserEntity_clientId);
	
	List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate, Date endDate, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(Date beginDate, Date endDate, String UserEntity_clientId);
	
}
