package cn.xsdzq.platform.dao;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.entity.InfoEntity;

@Repository
public interface KCRepository {
	void addInfo(CustomerMobileEntity customerMobileEntity);
}
