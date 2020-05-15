package cn.xsdzq.platform.service.thx;

import java.util.List;

import cn.xsdzq.platform.entity.thx.UserRiskEntity;

public interface UserRiskService {
	int countAll();
	List<UserRiskEntity> getAllUserRisk(int pageNumber, int pageSize);
}
