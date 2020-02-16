package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.AwardResultViewEntity;

public interface AwardResultRepository extends PagingAndSortingRepository<AwardResultViewEntity, Long> {
Page<AwardResultViewEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName, String clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String prizeName, String clientId);
	

	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqual(Date beginDate);

	Page<AwardResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTimeDesc( Date endDate, Pageable pageable);
	int countByRecordTimeLessThanEqual( Date endDate);
	
	Page<AwardResultViewEntity> findByPrizeCodeOrderByRecordTimeDesc(String prizeName, Pageable pageable);
	int countByPrizeCode(String prizeName);
	
	Page<AwardResultViewEntity> findByClientIdOrderByRecordTimeDesc(String clientId, Pageable pageable);
	int countByClientId(String clientId);
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(Date beginDate, Date endDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTimeDesc(Date beginDate, String prizeName, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String prizeName);
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTimeDesc(Date beginDate, String clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String clientId);
	
	Page<AwardResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc( Date endDate, String prizeName, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeCode(Date endDate, String prizeName);
	
	Page<AwardResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(Date endDate, String clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndClientId(Date endDate, String clientId);
	
	Page<AwardResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTimeDesc(String prizeName, String clientId, Pageable pageable);
	int countByPrizeCodeAndClientId (String prizeName, String clientId);
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate, String prizeName );
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(Date beginDate, String prizeName, String clientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate,  String prizeName, String clientId);
	
	
	Page<AwardResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc( Date endDate, String prizeName, String clientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId( Date endDate, String prizeName, String clientId);
	
	Page<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String clientId , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate, Date endDate, String clientId );

}
