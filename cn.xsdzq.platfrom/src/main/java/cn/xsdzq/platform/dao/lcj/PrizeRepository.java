package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.PrizeEntity;

@Repository
public interface PrizeRepository {
	List<PrizeEntity> getAllPrize();
	void deletePrize(PrizeEntity entity);

	void addPrize(PrizeEntity entity);

	void modifyPrize(PrizeEntity entity);
}
