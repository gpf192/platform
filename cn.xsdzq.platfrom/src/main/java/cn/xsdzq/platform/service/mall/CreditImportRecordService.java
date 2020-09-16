package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;

public interface CreditImportRecordService {
	void addRecord(CreditRecordEntity entity);
	List<CreditRecordEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<CreditRecordEntity> findByClientIdOrderByBeginDateDesc(String clientId, int pageNumber, int pageSize);
	int countByClientId(String clientId);
}
