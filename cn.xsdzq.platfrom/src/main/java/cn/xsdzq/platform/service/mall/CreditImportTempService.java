package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;

public interface CreditImportTempService {
	public void add(CreditImportTempEntity entity);
	public void deleteAllTemp();
	List<CreditImportTempEntity> findAllTemp();
	List<CreditImportTempEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<CreditImportTempEntity> findByClientIdOrderByBeginDateDesc(String clientId, int pageNumber, int pageSize);
	int countByClientId(String clientId);
}
