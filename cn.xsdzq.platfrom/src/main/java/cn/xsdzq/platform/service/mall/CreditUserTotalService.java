package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditUserTotalEntity;

public interface CreditUserTotalService {
	List<CreditUserTotalEntity> findByOrderByTotalDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<CreditUserTotalEntity> findByClientIdOrderByTotalDesc(String clientId, int pageNumber, int pageSize);
	int countByClientId(String clientId);
}
