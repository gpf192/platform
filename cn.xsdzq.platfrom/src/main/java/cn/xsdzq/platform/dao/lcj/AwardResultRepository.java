package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.AwardResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;

public interface AwardResultRepository extends PagingAndSortingRepository<AwardResultEntity, Long> {
Page<AwardResultEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId);
	

	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqual(Date beginDate);

	Page<AwardResultEntity> findByRecordTimeLessThanEqualOrderByRecordTimeDesc( Date endDate, Pageable pageable);
	int countByRecordTimeLessThanEqual( Date endDate);
	
	Page<AwardResultEntity> findByAwardEntity_imageNameOrderByRecordTimeDesc(String prizeName, Pageable pageable);
	int countByAwardEntity_imageName(String prizeName);
	
	Page<AwardResultEntity> findByUserEntity_clientIdOrderByRecordTimeDesc(String UserEntity_clientId, Pageable pageable);
	int countByUserEntity_clientId(String UserEntity_clientId);
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(Date beginDate, Date endDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameOrderByRecordTimeDesc(Date beginDate, String prizeName, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndAwardEntity_imageName(Date beginDate, String prizeName);
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId);
	
	Page<AwardResultEntity> findByRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTimeDesc( Date endDate, String prizeName, Pageable pageable);
	int countByRecordTimeLessThanEqualAndAwardEntity_imageName(Date endDate, String prizeName);
	
	Page<AwardResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(Date endDate, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId);
	
	Page<AwardResultEntity> findByAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByAwardEntity_imageNameAndUserEntity_clientId (String prizeName, String UserEntity_clientId);
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageName(Date beginDate, Date endDate, String prizeName );
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date beginDate,  String prizeName, String UserEntity_clientId);
	
	
	Page<AwardResultEntity> findByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc( Date endDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId( Date endDate, String prizeName, String UserEntity_clientId);
	
	Page<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String UserEntity_clientId , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(Date beginDate, Date endDate, String UserEntity_clientId );
}
