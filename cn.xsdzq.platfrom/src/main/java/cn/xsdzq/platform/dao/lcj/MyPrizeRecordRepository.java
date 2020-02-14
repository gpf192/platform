package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;

public interface MyPrizeRecordRepository extends PagingAndSortingRepository<PrizeResultEntity, Long> {
	Page<PrizeResultEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate, Date endDate, String prizeName, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqual(Date beginDate);

	Page<PrizeResultEntity> findByRecordTimeLessThanEqualOrderByRecordTimeDesc( Date endDate, Pageable pageable);
	int countByRecordTimeLessThanEqual( Date endDate);
	
	Page<PrizeResultEntity> findByPrizeEntity_nameOrderByRecordTimeDesc(String prizeName, Pageable pageable);
	int countByPrizeEntity_name(String prizeName);
	
	Page<PrizeResultEntity> findByUserEntity_clientIdOrderByRecordTimeDesc(String UserEntity_clientId, Pageable pageable);
	int countByUserEntity_clientId(String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(Date beginDate, Date endDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameOrderByRecordTimeDesc(Date beginDate, String prizeName, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeEntity_name(Date beginDate, String prizeName);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTimeDesc( Date endDate, String prizeName, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeEntity_name(Date endDate, String prizeName);
	
	Page<PrizeResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(Date endDate, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByPrizeEntity_nameAndUserEntity_clientId (String prizeName, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_name(Date beginDate, Date endDate, String prizeName );
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate,  String prizeName, String UserEntity_clientId);
	
	
	Page<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc( Date endDate, String prizeName, String UserEntity_clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId( Date endDate, String prizeName, String UserEntity_clientId);
	
	Page<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String UserEntity_clientId , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(Date beginDate, Date endDate, String UserEntity_clientId );
}
