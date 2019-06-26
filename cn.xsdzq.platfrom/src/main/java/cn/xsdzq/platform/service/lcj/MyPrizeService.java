package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.PrizeEntity;

public interface MyPrizeService {
	int countAll();
	List<PrizeEntity> getAllPrize(int pageNumber, int pageSize);
}
