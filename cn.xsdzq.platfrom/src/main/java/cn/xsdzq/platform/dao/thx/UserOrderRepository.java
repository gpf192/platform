package cn.xsdzq.platform.dao.thx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

public interface UserOrderRepository extends PagingAndSortingRepository<ThxOrderEntity, Long> {
	Page<ThxOrderEntity> findByOrderById(Pageable pageable);
	
	Page<ThxOrderEntity> findByUsernameLikeAndOrderIdLikeAndTgNameLikeAndProductNameLikeOrderByOrderId(String username, String orderId, String tgName, String productName, Pageable pageable);
	int countByUsernameLikeAndOrderIdLikeAndTgNameLikeAndProductNameLike(String username, String orderId, String tgName, String productName);
	
	Page<ThxOrderEntity> findByUsernameLikeAndOrderIdLikeAndTgNameLikeOrderByOrderId(String username, String orderId, String tgName, Pageable pageable);
	int countByUsernameLikeAndOrderIdLikeAndTgNameLike(String username, String orderId, String tgName);
	
	Page<ThxOrderEntity> findByUsernameLikeAndOrderIdLikeAndProductNameLikeOrderByOrderId(String username, String orderId, String productName, Pageable pageable);
	int countByUsernameLikeAndOrderIdLikeAndProductNameLike(String username, String orderId, String productName);
	
	Page<ThxOrderEntity> findByUsernameLikeAndTgNameLikeAndProductNameLikeOrderByOrderId(String username , String tgName, String productName, Pageable pageable);
	int countByUsernameLikeAndTgNameLikeAndProductNameLike(String username , String tgName, String productName);
	
	Page<ThxOrderEntity> findByOrderIdLikeAndTgNameLikeAndProductNameLikeOrderByOrderId( String orderId, String tgName, String productName, Pageable pageable);
	int countByOrderIdLikeAndTgNameLikeAndProductNameLike(String orderId, String tgName, String productName);
	
	Page<ThxOrderEntity> findByUsernameLikeAndOrderIdLikeOrderByOrderId(String username, String orderId,  Pageable pageable);
	int countByUsernameLikeAndOrderIdLike(String username, String orderId);
	
	Page<ThxOrderEntity> findByUsernameLikeAndTgNameLikeOrderByOrderId(String username, String tgName,  Pageable pageable);
	int countByUsernameLikeAndTgNameLike(String username,   String tgName);
	
	Page<ThxOrderEntity> findByUsernameLikeAndProductNameLikeOrderByOrderId(String username,  String productName, Pageable pageable);
	int countByUsernameLikeAndProductNameLike(String username,  String productName);
	
	Page<ThxOrderEntity> findByOrderIdLikeAndTgNameLikeOrderByOrderId( String orderId, String tgName, Pageable pageable);
	int countByOrderIdLikeAndTgNameLike(String orderId, String tgName);
	
	
	Page<ThxOrderEntity> findByOrderIdLikeAndProductNameLikeOrderByOrderId(String orderId, String productName, Pageable pageable);
	int countByOrderIdLikeAndProductNameLike( String orderId, String productName);
	
	Page<ThxOrderEntity> findByTgNameLikeAndProductNameLikeOrderByOrderId(String tgName, String productName, Pageable pageable);
	int countByTgNameLikeAndProductNameLike(String tgName, String productName);
	
	Page<ThxOrderEntity> findByUsernameLikeOrderByOrderId(String username, Pageable pageable);
	int countByUsernameLike(String username);
	
	Page<ThxOrderEntity> findByOrderIdLikeOrderByOrderId( String orderId, Pageable pageable);
	int countByOrderIdLike( String orderId);
	
	Page<ThxOrderEntity> findByTgNameLikeOrderByOrderId(String tgName, Pageable pageable);
	int countByTgNameLike(String tgName);
	
	Page<ThxOrderEntity> findByProductNameLikeOrderByOrderId(String productName, Pageable pageable);
	int countByProductNameLike(String productName);
	
	

}
