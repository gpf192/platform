package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CardImportTempEntity;

public interface PageCardImportTempService {
	public void add(CardImportTempEntity entity);
	public void deleteAllTemp();
	List<CardImportTempEntity> findAllTemp();
	List<CardImportTempEntity> findByOrderByCardId(int pageNumber, int pageSize);
	int countAll();
}
