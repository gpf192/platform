package cn.xsdzq.platform.service;

import java.util.Date;
import java.util.List;

import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.util.DateUtil;

public interface KCService {
	int countAll();
	List<CustomerMobileEntity> getKCInfos(int pageNumber, int pageSize);
	List<CustomerMobileEntity> findByEndTimeLessThanEqualAndBeginTimeGreaterThanEqualOrderByRecordTimeDesc(Date endDate, Date beginDate,  int pageNumber, int pageSize);
	int countByEndTimeLessThanEqualAndBeginTimeGreaterThanEqual(Date endDate, Date beginDate); 
	
	List<CustomerMobileEntity> findByBeginTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate,  int pageNumber, int pageSize);
	int countByBeginTimeGreaterThanEqual(Date beginDate);
	
	List<CustomerMobileEntity> findByEndTimeLessThanEqualOrderByRecordTimeDesc(Date endDate, int pageNumber, int pageSize);
	int countByEndTimeLessThanEqual(Date endDate);
	
}
