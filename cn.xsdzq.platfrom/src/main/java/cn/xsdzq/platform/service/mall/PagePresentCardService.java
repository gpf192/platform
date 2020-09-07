package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;

public interface PagePresentCardService {
	List<PresentCardEntity> findByOrderByCreateDateDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<PresentCardEntity> findByCardIdOrderByCreateDateDesc(String cardId, int pageNumber, int pageSize);
	int countByCardId(String cardId);
}
