package cn.xsdzq.platform.service.thx;

import java.util.List;

import org.springframework.data.domain.Page;
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
	
	List<ThxOrderEntity> findByUsernameAndOrderIdOrderByOrderId(String username, String orderId, int pageNumber, int pageSize);
	int countByUsernameAndOrderId(String username, String orderId); 
	
	List<ThxOrderEntity> findByUsernameAndTgNameOrderByOrderId(String username,   String tgName , int pageNumber, int pageSize);
	int countByUsernameAndTgName(String username , String tgName ); 
	
	List<ThxOrderEntity> findByUsernameAndProductNameOrderByOrderId(String username, String productName, int pageNumber, int pageSize);
	int countByUsernameAndProductName(String username,  String productName); 
	
	List<ThxOrderEntity> findByOrderIdAndTgNameOrderByOrderId(String orderId, String tgName, int pageNumber, int pageSize);
	int countByOrderIdAndTgName(String orderId,  String tgName); 
	
	List<ThxOrderEntity> findByOrderIdAndProductNameOrderByOrderId(String orderId, String productName, int pageNumber, int pageSize);
	int countByOrderIdAndProductName(String orderId,  String productName); 
	
	List<ThxOrderEntity> findByTgNameAndProductNameOrderByOrderId(String tgName, String productName, int pageNumber, int pageSize);
	int countByTgNameAndProductName(String tgName,  String productName); 
	
	List<ThxOrderEntity> findByUsernameOrderByOrderId(String username, int pageNumber, int pageSize);
	int countByUsername(String username);
	
	List<ThxOrderEntity> findByOrderIdOrderByOrderId( String orderId, int pageNumber, int pageSize);
	int countByOrderId( String orderId);
	
	List<ThxOrderEntity> findByTgNameOrderByOrderId(String tgName,int pageNumber, int pageSize);
	int countByTgName(String tgName);
	
	List<ThxOrderEntity> findByProductNameOrderByOrderId(String productName, int pageNumber, int pageSize);
	int countByProductName(String productName);
}
