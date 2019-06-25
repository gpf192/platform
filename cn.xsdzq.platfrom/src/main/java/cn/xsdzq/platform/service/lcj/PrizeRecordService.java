package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;

public interface PrizeRecordService {
	int countAll();
	List<PrizeRecordEntity> getAllPrizeRecord(int pageNumber, int pageSize);
}
