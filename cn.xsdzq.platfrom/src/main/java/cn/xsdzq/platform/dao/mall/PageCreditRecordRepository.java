package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;

public interface PageCreditRecordRepository extends PagingAndSortingRepository<CreditRecordEntity, Long> {
	Page<CreditRecordEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	//
	Page<CreditRecordEntity> findByMallUserEntity_ClientNameLikeOrderByRecordTimeDesc(String username, Pageable pageable);
	int countByMallUserEntity_ClientNameLike(String username);
	
	Page<CreditRecordEntity> findByClientIdLikeOrderByRecordTimeDesc(String clientId, Pageable pageable);
	int countByClientIdLike(String clientId);
	
	Page<CreditRecordEntity> findByItemCodeLikeOrderByRecordTimeDesc(String itemCode, Pageable pageable);
	int countByItemCodeLike(String itemCode);
	//
	
	Page<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndClientIdLikeOrderByRecordTimeDesc(String username,String clientId, Pageable pageable);
	int countByMallUserEntity_ClientNameLikeAndClientIdLike(String username,String clientId);
	
	Page<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndItemCodeLikeOrderByRecordTimeDesc(String username,String itemCode, Pageable pageable);
	int countByMallUserEntity_ClientNameLikeAndItemCodeLike(String username,String itemCode);
	
	Page<CreditRecordEntity> findByClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(String clientId, String itemCode,Pageable pageable);
	int countByClientIdLikeAndItemCodeLike(String clientId,String itemCode);
	//
	Page<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(String username,String clientId, String itemCode,Pageable pageable);
	int countByMallUserEntity_ClientNameLikeAndClientIdLikeItemCodeLike(String username,String clientId,String itemCode);
}
