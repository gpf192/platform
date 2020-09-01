package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.xsdzq.platform.entity.lcj.LcjPrizeEntity;

@Repository
public interface LcjPrizeRepository {
	LcjPrizeEntity getPrizeById(long id);
	List<LcjPrizeEntity> getAllPrize();
	void deletePrize(LcjPrizeEntity entity);

	void addPrize(LcjPrizeEntity entity);

	void modifyPrize(LcjPrizeEntity entity);
}
