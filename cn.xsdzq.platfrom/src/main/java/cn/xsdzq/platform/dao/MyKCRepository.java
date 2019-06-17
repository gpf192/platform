package cn.xsdzq.platform.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.xsdzq.platform.entity.CustomerMobileEntity;

public interface MyKCRepository  extends PagingAndSortingRepository<CustomerMobileEntity, Long> {
	Page<CustomerMobileEntity> findByOrderByCreatetimeDesc(Pageable pageable);
	Page<CustomerMobileEntity> findByCreatetimeLessThanEqualAndCreatetimeGreaterThanEqualOrderByCreatetimeDesc(Date endDate, Date beginDate, Pageable pageable);
	int countByCreatetimeLessThanEqualAndCreatetimeGreaterThanEqual(Date endDate, Date beginDate);
	
	Page<CustomerMobileEntity> findByCreatetimeGreaterThanEqualOrderByCreatetimeDesc(Date beginDate, Pageable pageRequest);
	int countByCreatetimeGreaterThanEqual(Date beginDate);
	
	Page<CustomerMobileEntity> findByCreatetimeLessThanEqualOrderByCreatetimeDesc(Date endDate, Pageable pageRequest);
	int countByCreatetimeLessThanEqual(Date endDate);
}
