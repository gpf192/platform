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
	
	Page<ThxOrderEntity> findByUsernameAndOrderIdOrderByOrderId(String username, String orderId,  Pageable pageable);
	int countByUsernameAndOrderId(String username, String orderId);
	
	Page<ThxOrderEntity> findByUsernameAndTgNameOrderByOrderId(String username, String tgName,  Pageable pageable);
	int countByUsernameAndTgName(String username,   String tgName);
	
	Page<ThxOrderEntity> findByUsernameAndProductNameOrderByOrderId(String username,  String productName, Pageable pageable);
	int countByUsernameAndProductName(String username,  String productName);
	
	Page<ThxOrderEntity> findByOrderIdAndTgNameOrderByOrderId( String orderId, String tgName, Pageable pageable);
	int countByOrderIdAndTgName(String orderId, String tgName);
	
	
	Page<ThxOrderEntity> findByOrderIdAndProductNameOrderByOrderId(String orderId, String productName, Pageable pageable);
	int countByOrderIdAndProductName( String orderId, String productName);
	
	Page<ThxOrderEntity> findByTgNameAndProductNameOrderByOrderId(String tgName, String productName, Pageable pageable);
	int countByTgNameAndProductName(String tgName, String productName);
	
	Page<ThxOrderEntity> findByUsernameOrderByOrderId(String username, Pageable pageable);
	int countByUsername(String username);
	
	Page<ThxOrderEntity> findByOrderIdOrderByOrderId( String orderId, Pageable pageable);
	int countByOrderId( String orderId);
	
	Page<ThxOrderEntity> findByTgNameOrderByOrderId(String tgName, Pageable pageable);
	int countByTgName(String tgName);
	
	Page<ThxOrderEntity> findByProductNameOrderByOrderId(String productName, Pageable pageable);
	int countByProductName(String productName);
	
	

}
