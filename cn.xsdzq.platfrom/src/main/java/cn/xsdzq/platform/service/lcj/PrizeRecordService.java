package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;

public interface PrizeRecordService {
	int countAll();
	List<PrizeRecordEntity> getAllPrizeRecord(int pageNumber, int pageSize);
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(Date beginDate, Date endDate, String prizeName, String account, int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccount(Date beginDate, Date endDate, String prizeName, String account);
	
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualOrderByCreatetime(Date beginDate, int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqual(Date beginDate);
	
	List<PrizeRecordEntity> findByCreatetimeLessThanEqualOrderByCreatetime(Date endDate, int pageNumber, int pageSize);
	int countByCreatetimeLessThanEqual(Date beginDate);
	
	List<PrizeRecordEntity> findByPrizeNameOrderByCreatetime( String prizeName, int pageNumber, int pageSize);
	int countByPrizeName(String prizeName);
	
	List<PrizeRecordEntity> findByAccountOrderByCreatetime( String account, int pageNumber, int pageSize);
	int countByAccount(String account);
	
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualOrderByCreatetime(Date beginDate, Date endDate, int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqual(Date beginDate, Date endDate);
	
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndPrizeNameOrderByCreatetime(Date beginDate,  String prizeName,  int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqualAndPrizeName(Date beginDate, String prizeName);
	
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndAccountOrderByCreatetime(Date beginDate,  String account,  int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqualAndAccount(Date beginDate, String account);
	
	List<PrizeRecordEntity> findByCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime( Date endDate, String prizeName, int pageNumber, int pageSize);
	int countByCreatetimeLessThanEqualAndPrizeName( Date endDate, String prizeName);
	
	List<PrizeRecordEntity> findByCreatetimeLessThanEqualAndAccountOrderByCreatetime(Date endDate, String account, int pageNumber, int pageSize);
	int countByCreatetimeLessThanEqualAndAccount(Date endDate, String account);
	
	List<PrizeRecordEntity> findByPrizeNameAndAccountOrderByCreatetime(String prizeName, String account, int pageNumber, int pageSize);
	int countByPrizeNameAndAccount (String prizeName, String account);
	
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(Date beginDate, Date endDate, String prizeName, int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeName(Date beginDate, Date endDate, String prizeName);
	
	List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndPrizeNameAndAccountOrderByCreatetime(Date beginDate,  String prizeName, String account, int pageNumber, int pageSize);
	int countByCreatetimeGreaterThanEqualAndPrizeNameAndAccount(Date beginDate,  String prizeName, String account);
	
	List<PrizeRecordEntity> findByCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(Date endDate,  String prizeName, String account, int pageNumber, int pageSize);
	int countByCreatetimeLessThanEqualAndPrizeNameAndAccount(Date endDate,  String prizeName, String account);
	
}
