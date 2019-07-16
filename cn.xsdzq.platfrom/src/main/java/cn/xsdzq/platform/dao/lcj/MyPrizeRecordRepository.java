package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;

public interface MyPrizeRecordRepository extends PagingAndSortingRepository<PrizeResultEntity, Long> {
	Page<PrizeResultEntity> findByOrderByRecordTime(Pageable pageable);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqual(Date beginDate);

	Page<PrizeResultEntity> findByRecordTimeLessThanEqualOrderByRecordTime( Date endDate, Pageable pageable);
	int countByRecordTimeLessThanEqual( Date endDate);
	
	Page<PrizeResultEntity> findByPrizeEntity_nameOrderByRecordTime(String prizeName, Pageable pageable);
	int countByPrizeEntity_name(String prizeName);
	
	Page<PrizeResultEntity> findByUserEntity_clientIdOrderByRecordTime(String UserEntity_clientId, Pageable pageable);
	int countByUserEntity_clientId(String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(Date beginDate, Date endDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameOrderByRecordTime(Date beginDate, String prizeName, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeEntity_name(Date beginDate, String prizeName);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime( Date endDate, String prizeName, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeEntity_name(Date endDate, String prizeName);
	
	Page<PrizeResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date endDate, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByPrizeEntity_nameAndUserEntity_clientId (String prizeName, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_name(Date beginDate, Date endDate, String prizeName );
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(Date beginDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate,  String prizeName, String UserEntity_clientId);
	
	
	Page<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime( Date endDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId( Date endDate, String prizeName, String UserEntity_clientId);
}
