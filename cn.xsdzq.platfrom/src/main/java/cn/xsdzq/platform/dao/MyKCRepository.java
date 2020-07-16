package cn.xsdzq.platform.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.xsdzq.platform.entity.CustomerMobileEntity;

public interface MyKCRepository  extends PagingAndSortingRepository<CustomerMobileEntity, Long> {
	Page<CustomerMobileEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	Page<CustomerMobileEntity> findByRecordTimeLessThanEqualAndRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date endDate, Date beginDate, Pageable pageable);
	int countByRecordTimeLessThanEqualAndRecordTimeGreaterThanEqual(Date endDate, Date beginDate);
	
	Page<CustomerMobileEntity> findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate, Pageable pageRequest);
	int countByRecordTimeGreaterThanEqual(Date beginDate);
	
	Page<CustomerMobileEntity> findByRecordTimeLessThanEqualOrderByRecordTimeDesc(Date endDate, Pageable pageRequest);
	int countByRecordTimeLessThanEqual(Date endDate);
}
