package cn.xsdzq.platform.dao.thx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

public interface UserOrderRepository extends PagingAndSortingRepository<ThxOrderEntity, Long> {
	Page<ThxOrderEntity> findByOrderById(Pageable pageable);
	
	Page<ThxOrderEntity> findByUsernameAndOrderIdAndTgNameAndProductNameOrderByOrderId(String username, String orderId, String tgName, String productName, Pageable pageable);
	int countByUsernameAndOrderIdAndTgNameAndProductName(String username, String orderId, String tgName, String productName);
	
	Page<ThxOrderEntity> findByUsernameAndOrderIdAndTgNameOrderByOrderId(String username, String orderId, String tgName, Pageable pageable);
	int countByUsernameAndOrderIdAndTgName(String username, String orderId, String tgName);
	
	Page<ThxOrderEntity> findByUsernameAndOrderIdAndProductNameOrderByOrderId(String username, String orderId, String productName, Pageable pageable);
	int countByUsernameAndOrderIdAndProductName(String username, String orderId, String productName);
	
	Page<ThxOrderEntity> findByUsernameAndTgNameAndProductNameOrderByOrderId(String username , String tgName, String productName, Pageable pageable);
	int countByUsernameAndTgNameAndProductName(String username , String tgName, String productName);
	
	Page<ThxOrderEntity> findByOrderIdAndTgNameAndProductNameOrderByOrderId( String orderId, String tgName, String productName, Pageable pageable);
	int countByOrderIdAndTgNameAndProductName(String orderId, String tgName, String productName);
	
	
	
	
	
	
	Page<ThxOrderEntity> findByUserEntity_clientNameOrderByRecordTime(String username, Pageable pageable);
	int countByUserEntity_clientName(String username);
	

}
