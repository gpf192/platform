package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CRMCreditProductViewEntity;

public interface CRMCreditProductService {
	List<CRMCreditProductViewEntity> findByOrderByProductCode(int pageNumber, int pageSize);
	 int countAll();
}
