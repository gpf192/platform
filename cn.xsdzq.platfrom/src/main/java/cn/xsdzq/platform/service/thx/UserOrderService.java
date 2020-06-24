package cn.xsdzq.platform.service.thx;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

public interface UserOrderService {
	int countAll();
	List<ThxOrderEntity> getAllProduct(int pageNumber, int pageSize);
	
	List<ThxOrderEntity> findByUsernameAndOrderIdAndTgNameAndProductNameOrderByOrderId(String username, String orderId, String tgName, String productName, int pageNumber, int pageSize);
	int countByUsernameAndOrderIdAndTgNameAndProductName(String username, String orderId, String tgName, String productName); 
	
	List<ThxOrderEntity> findByUsernameAndOrderIdAndTgNameOrderByOrderId(String username, String orderId, String tgName , int pageNumber, int pageSize);
	int countByUsernameAndOrderIdAndTgName(String username, String orderId, String tgName ); 

	List<ThxOrderEntity> findByUsernameAndOrderIdAndProductNameOrderByOrderId(String username, String orderId,  String productName, int pageNumber, int pageSize);
	int countByUsernameAndOrderIdAndProductName(String username, String orderId, String productName); 
	
	List<ThxOrderEntity> findByUsernameAndTgNameAndProductNameOrderByOrderId(String username, String tgName, String productName, int pageNumber, int pageSize);
	int countByUsernameAndTgNameAndProductName(String username , String tgName, String productName); 
	
	List<ThxOrderEntity> findByOrderIdAndTgNameAndProductNameOrderByOrderId( String orderId, String tgName, String productName, int pageNumber, int pageSize);
	int countByOrderIdAndTgNameAndProductName( String orderId, String tgName, String productName); 
	
	
}
