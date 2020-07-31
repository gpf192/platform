package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.LcjPrizeEntity;

public interface LcjPrizeService {
	List<LcjPrizeEntity> getAllPrize();
	void addPrize(LcjPrizeEntity entity);

	void deletePrize(LcjPrizeEntity entity);

	void modifyPrize(LcjPrizeEntity entity);
}
