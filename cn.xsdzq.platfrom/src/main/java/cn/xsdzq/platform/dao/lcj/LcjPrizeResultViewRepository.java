package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.LcjPrizeResultViewEntity;

public interface LcjPrizeResultViewRepository extends PagingAndSortingRepository<LcjPrizeResultViewEntity, Long> {
	Page<LcjPrizeResultViewEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName, String ClientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String prizeName, String ClientId);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqual(Date beginDate);

	Page<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTimeDesc( Date endDate, Pageable pageable);
	int countByRecordTimeLessThanEqual( Date endDate);
	
	Page<LcjPrizeResultViewEntity> findByPrizeCodeOrderByRecordTimeDesc(String prizeName, Pageable pageable);
	int countByPrizeCode(String prizeName);
	
	Page<LcjPrizeResultViewEntity> findByClientIdOrderByRecordTimeDesc(String ClientId, Pageable pageable);
	int countByClientId(String ClientId);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(Date beginDate, Date endDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTimeDesc(Date beginDate, String prizeName, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String prizeName);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTimeDesc(Date beginDate, String ClientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc( Date endDate, String prizeName, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeCode(Date endDate, String prizeName);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(Date endDate, String ClientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId);
	
	Page<LcjPrizeResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTimeDesc(String prizeName, String ClientId, Pageable pageable);
	int countByPrizeCodeAndClientId (String prizeName, String ClientId);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate, String prizeName );
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(Date beginDate, String prizeName, String ClientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate,  String prizeName, String ClientId);
	
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc( Date endDate, String prizeName, String ClientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId( Date endDate, String prizeName, String ClientId);
	
	Page<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String ClientId , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate, Date endDate, String ClientId );

}
