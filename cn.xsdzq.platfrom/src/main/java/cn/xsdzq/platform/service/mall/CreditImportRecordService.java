package cn.xsdzq.platform.service.mall;

import java.util.List;


import cn.xsdzq.platform.entity.mall.CreditRecordEntity;

public interface CreditImportRecordService {
	void addRecord(CreditRecordEntity entity);
	//分页查询
	List<CreditRecordEntity> findByOrderByRecordTimeDesc(int pageNumber, int pageSize);
	int countAll();
	//
	List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeOrderByRecordTimeDesc(String username, int pageNumber, int pageSize);
	int countByMallUserEntity_ClientNameLike(String username);
	
	List<CreditRecordEntity> findByClientIdLikeOrderByRecordTimeDesc(String clientId, int pageNumber, int pageSize);
	int countByClientIdLike(String clientId);
	
	List<CreditRecordEntity> findByItemCodeLikeOrderByRecordTimeDesc(String itemCode, int pageNumber, int pageSize);
	int countByItemCodeLike(String itemCode);
	//
	
	List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndClientIdLikeOrderByRecordTimeDesc(String username,String clientId,  int pageNumber, int pageSize);
	int countByMallUserEntity_ClientNameLikeAndClientIdLike(String username,String clientId);
	
	List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndItemCodeLikeOrderByRecordTimeDesc(String username,String itemCode, int pageNumber, int pageSize);
	int countByMallUserEntity_ClientNameLikeAndItemCodeLike(String username,String itemCode);
	
	List<CreditRecordEntity> findByClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(String clientId, String itemCode, int pageNumber, int pageSize);
	int countByClientIdLikeAndItemCodeLike(String clientId,String itemCode);
	//
	List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(String username,String clientId, String itemCode, int pageNumber, int pageSize);
	int countByMallUserEntity_ClientNameLikeAndClientIdLikeItemCodeLike(String username,String clientId,String itemCode);
	
	int countByClientIdAndItemCodeAndBeginDateAndType(String clientId,String itemCode,String beginDate);
	int countByTypeAndItemCode(boolean type ,String itemCode);
	
	List<CreditRecordEntity> findByType(boolean type);
	
}
