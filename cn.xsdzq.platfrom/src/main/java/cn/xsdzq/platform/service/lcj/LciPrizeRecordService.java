package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.LcjPrizeNumberEntity;
import cn.xsdzq.platform.entity.lcj.LcjPrizeRecordEntity;

public interface LciPrizeRecordService {
	//818 LcjPrizeRecordEntity
	int countPrizeRecodAll();	
	List<LcjPrizeRecordEntity> getAllPrizeRecordForKmh(int pageNumber, int pageSize);
	
	List<LcjPrizeRecordEntity> findRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(String clientName,  String clientId,int pageNumber, int pageSize);
	int countRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName,  String clientId);
	
	List<LcjPrizeRecordEntity> findRecordByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, int pageNumber, int pageSize);
	int countRecordByUserEntity_clientNameLike(String clientName );
	
	List<LcjPrizeRecordEntity> findRecordByUserEntity_clientIdLikeOrderByNumberDesc( String clientId, int pageNumber, int pageSize);
	int countRecordByUserEntity_clientIdLike( String clientId);
	
	//818 LcjPrizeNumberEntity
	int countPrizeNumberForKmh();
	List<LcjPrizeNumberEntity> getPrizeNumberForKmh(int pageNumber, int pageSize);
	
	List<LcjPrizeNumberEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(String clientName,  String clientId,int pageNumber, int pageSize);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName,  String clientId);
	
	List<LcjPrizeNumberEntity> findByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, int pageNumber, int pageSize);
	int countByUserEntity_clientNameLike(String clientName );
	
	List<LcjPrizeNumberEntity> findByUserEntity_clientIdLikeOrderByNumberDesc( String clientId, int pageNumber, int pageSize);
	int countByUserEntity_clientIdLike( String clientId);
}
