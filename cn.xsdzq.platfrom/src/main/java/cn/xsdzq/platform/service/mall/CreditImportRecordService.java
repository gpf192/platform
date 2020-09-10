package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditImportRecordEntity;

public interface CreditImportRecordService {
	List<CreditImportRecordEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<CreditImportRecordEntity> findByClientIdOrderByBeginDateDesc(String clientId, int pageNumber, int pageSize);
	int countByClientId(String clientId);
}
