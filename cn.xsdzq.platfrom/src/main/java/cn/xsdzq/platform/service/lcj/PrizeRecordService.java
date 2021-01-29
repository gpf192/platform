package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.xsdzq.platform.entity.lcj.PrizeNumberEntity;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;

public interface PrizeRecordService {
	

	int countAll();	
	List<PrizeResultViewEntity> getAllPrizeRecord(int pageNumber, int pageSize);
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date beginDate, Date endDate, String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String PrizeCode, String ClientId);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqual(Date beginDate);
	
	List<PrizeResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqual(Date beginDate);
	
	List<PrizeResultViewEntity> findByPrizeCodeOrderByRecordTime( String PrizeCode, int pageNumber, int pageSize);
	int countByPrizeCode(String PrizeCode);
	
	List<PrizeResultViewEntity> findByClientIdOrderByRecordTime( String ClientId, int pageNumber, int pageSize);
	int countByClientId(String ClientId);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(Date beginDate, Date endDate, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate,  String PrizeCode,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String PrizeCode);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(Date beginDate,  String ClientId,  int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId);
	
	List<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime( Date endDate, String PrizeCode, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeCode( Date endDate, String PrizeCode);
	
	List<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date endDate, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId);
	
	List<PrizeResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTime(String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByPrizeCodeAndClientId (String PrizeCode, String ClientId);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate, Date endDate, String PrizeCode, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate, String PrizeCode);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date beginDate,  String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate,  String PrizeCode, String ClientId);
	
	List<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date endDate,  String PrizeCode, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date endDate,  String PrizeCode, String ClientId);
	
	List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date beginDate, Date endDate, String ClientId, int pageNumber, int pageSize);
	int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate, Date endDate, String ClientId);
	//kmh  PrizeRecordEntity
	int countPrizeRecodAll();	
	List<PrizeRecordEntity> getAllPrizeRecordForKmh(int pageNumber, int pageSize);
	
	List<PrizeRecordEntity> findRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(String clientName,  String clientId,int pageNumber, int pageSize);
	int countRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName,  String clientId);
	
	List<PrizeRecordEntity> findRecordByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, int pageNumber, int pageSize);
	int countRecordByUserEntity_clientNameLike(String clientName );
	
	List<PrizeRecordEntity> findRecordByUserEntity_clientIdLikeOrderByNumberDesc( String clientId, int pageNumber, int pageSize);
	int countRecordByUserEntity_clientIdLike( String clientId);
	
	//PrizeNumberEntity
	int countPrizeNumberForKmh();
	List<PrizeNumberEntity> getPrizeNumberForKmh(int pageNumber, int pageSize);
	
	List<PrizeNumberEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(String clientName,  String clientId,int pageNumber, int pageSize);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName,  String clientId);
	
	List<PrizeNumberEntity> findByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, int pageNumber, int pageSize);
	int countByUserEntity_clientNameLike(String clientName );
	
	List<PrizeNumberEntity> findByUserEntity_clientIdLikeOrderByNumberDesc( String clientId, int pageNumber, int pageSize);
	int countByUserEntity_clientIdLike( String clientId);
}
