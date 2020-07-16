package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;

public interface MyPrizeRecordRepository extends PagingAndSortingRepository<PrizeResultViewEntity, Long> {
	Page<PrizeResultViewEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName, String ClientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String prizeName, String ClientId);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqual(Date beginDate);

	Page<PrizeResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTimeDesc( Date endDate, Pageable pageable);
	int countByRecordTimeLessThanEqual( Date endDate);
	
	Page<PrizeResultViewEntity> findByPrizeCodeOrderByRecordTimeDesc(String prizeName, Pageable pageable);
	int countByPrizeCode(String prizeName);
	
	Page<PrizeResultViewEntity> findByClientIdOrderByRecordTimeDesc(String ClientId, Pageable pageable);
	int countByClientId(String ClientId);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(Date beginDate, Date endDate, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTimeDesc(Date beginDate, String prizeName, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String prizeName);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTimeDesc(Date beginDate, String ClientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId);
	
	Page<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc( Date endDate, String prizeName, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeCode(Date endDate, String prizeName);
	
	Page<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(Date endDate, String ClientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId);
	
	Page<PrizeResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTimeDesc(String prizeName, String ClientId, Pageable pageable);
	int countByPrizeCodeAndClientId (String prizeName, String ClientId);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate, String prizeName );
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(Date beginDate, String prizeName, String ClientId, Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate,  String prizeName, String ClientId);
	
	
	Page<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc( Date endDate, String prizeName, String ClientId, Pageable pageable);
	int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId( Date endDate, String prizeName, String ClientId);
	
	Page<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(Date beginDate, Date endDate, String ClientId , Pageable pageable);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate, Date endDate, String ClientId );

}
