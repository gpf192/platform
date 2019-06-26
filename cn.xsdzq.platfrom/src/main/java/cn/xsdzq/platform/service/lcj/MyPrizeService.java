package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.PrizeEntity;

public interface MyPrizeService {
	List<PrizeEntity> getAllPrize();
	void addPrize(PrizeEntity entity);

	void deletePrize(PrizeEntity entity);

	void modifyPrize(PrizeEntity entity);
}
