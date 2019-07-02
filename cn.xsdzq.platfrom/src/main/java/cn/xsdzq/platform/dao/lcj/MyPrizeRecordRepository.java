package cn.xsdzq.platform.dao.lcj;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;

public interface MyPrizeRecordRepository extends PagingAndSortingRepository<PrizeRecordEntity, Long> {
	Page<PrizeRecordEntity> findByOrderById(Pageable pageable);
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(Date beginDate, Date endDate, String prizeName, String account, Pageable pageable);
	int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccount(Date beginDate, Date endDate, String prizeName, String account);
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualOrderByCreatetime(Date beginDate, Pageable pageable);
	int countByCreatetimeGreaterThanEqual(Date beginDate);

	Page<PrizeRecordEntity> findByCreatetimeLessThanEqualOrderByCreatetime( Date endDate, Pageable pageable);
	int countByCreatetimeLessThanEqual( Date endDate);
	
	Page<PrizeRecordEntity> findByPrizeNameOrderByCreatetime(String prizeName, Pageable pageable);
	int countByPrizeName(String prizeName);
	
	Page<PrizeRecordEntity> findByAccountOrderByCreatetime(String account, Pageable pageable);
	int countByAccount(String account);
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualOrderByCreatetime(Date beginDate, Date endDate, Pageable pageable);
	int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqual(Date beginDate, Date endDate);
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndPrizeNameOrderByCreatetime(Date beginDate, String prizeName, Pageable pageable);
	int countByCreatetimeGreaterThanEqualAndPrizeName(Date beginDate, String prizeName);
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndAccountOrderByCreatetime(Date beginDate, String account, Pageable pageable);
	int countByCreatetimeGreaterThanEqualAndAccount(Date beginDate, String account);
	
	Page<PrizeRecordEntity> findByCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime( Date endDate, String prizeName, Pageable pageable);
	int countByCreatetimeLessThanEqualAndPrizeName(Date endDate, String prizeName);
	
	Page<PrizeRecordEntity> findByCreatetimeLessThanEqualAndAccountOrderByCreatetime(Date endDate, String account, Pageable pageable);
	int countByCreatetimeLessThanEqualAndAccount(Date endDate, String account);
	
	Page<PrizeRecordEntity> findByPrizeNameAndAccountOrderByCreatetime(String prizeName, String account, Pageable pageable);
	int countByPrizeNameAndAccount (String prizeName, String account);
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(Date beginDate, Date endDate, String prizeName , Pageable pageable);
	int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeName(Date beginDate, Date endDate, String prizeName );
	
	Page<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndPrizeNameAndAccountOrderByCreatetime(Date beginDate, String prizeName, String account, Pageable pageable);
	int countByCreatetimeGreaterThanEqualAndPrizeNameAndAccount(Date beginDate,  String prizeName, String account);
	
	
	Page<PrizeRecordEntity> findByCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime( Date endDate, String prizeName, String account, Pageable pageable);
	int countByCreatetimeLessThanEqualAndPrizeNameAndAccount( Date endDate, String prizeName, String account);
}
