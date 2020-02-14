package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import cn.xsdzq.platform.entity.lcj.AwardResultEntity;

public interface AwardResultService {
	int countAll();	
	List<AwardResultEntity> getAllAwardResult(int pageNumber, int pageSize);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqual(Date beginDate);
	
	List<AwardResultEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqual(Date beginDate);
	
	List<AwardResultEntity> findByAwardEntity_imageNameOrderByRecordTime( String prizeName, int pageNumber, int pageSize);
	int countByAwardEntity_imageName(String prizeName);
	
	List<AwardResultEntity> findByUserEntity_clientIdOrderByRecordTime( String UserEntity_clientId, int pageNumber, int pageSize);
	int countByUserEntity_clientId(String UserEntity_clientId);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(Date beginDate, Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameOrderByRecordTime(Date beginDate,  String prizeName,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndAwardEntity_imageName(Date beginDate, String prizeName);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate,  String UserEntity_clientId,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId);
	
	List<AwardResultEntity> findByRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTime( Date endDate, String prizeName, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndAwardEntity_imageName( Date endDate, String prizeName);
	
	List<AwardResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date endDate, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId);
	
	List<AwardResultEntity> findByAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(String prizeName, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByAwardEntity_imageNameAndUserEntity_clientId (String prizeName, String UserEntity_clientId);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTime(Date beginDate, Date endDate, String prizeName, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageName(Date beginDate, Date endDate, String prizeName);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(Date beginDate,  String prizeName, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date beginDate,  String prizeName, String UserEntity_clientId);
	
	List<AwardResultEntity> findByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(Date endDate,  String prizeName, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date endDate,  String prizeName, String UserEntity_clientId);
	
	List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate, Date endDate, String UserEntity_clientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(Date beginDate, Date endDate, String UserEntity_clientId);
	
}
