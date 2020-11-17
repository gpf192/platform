package cn.xsdzq.platform.dao.mall;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;

public interface PageMallUserInfoRepository extends PagingAndSortingRepository<MallUserInfoEntity, Long>{
	Page<MallUserInfoEntity> findByOrderByCreditScoreDesc(Pageable pageable);
	//
	Page<MallUserInfoEntity> findByMallUserEntity_clientNameLikeOrderByCreditScoreDesc(String username, Pageable pageable);
	int countByMallUserEntity_clientNameLike(String username);
	
	Page<MallUserInfoEntity> findByClientIdLikeOrderByCreditScoreDesc(String clientId, Pageable pageable);
	int countByClientIdLike(String clientId);
	
	Page<MallUserInfoEntity> findByMallUserEntity_mobileLikeOrderByCreditScoreDesc(String moblie, Pageable pageable);
	int countByMallUserEntity_mobileLike(String moblie);
	//
	
	Page<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndClientIdLikeOrderByCreditScoreDesc(String username, String clientId, Pageable pageable);
	int countByMallUserEntity_clientNameLikeAndClientIdLike(String username, String clientId);
	
	Page<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndMallUserEntity_mobileLikeOrderByCreditScoreDesc(String username, String moblie, Pageable pageable);
	int countByMallUserEntity_clientNameLikeAndMallUserEntity_mobileLike(String username, String moblie);
	
	Page<MallUserInfoEntity> findByClientIdLikeAndMallUserEntity_mobileLikeOrderByCreditScoreDesc(String clientId, String moblie, Pageable pageable);
	int countByClientIdLikeAndMallUserEntity_mobileLike(String clientId, String moblie);
	//
	
	Page<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndClientIdLikeAndMallUserEntity_mobileLikeOrderByCreditScoreDesc(String username, String clientId, String moblie, Pageable pageable);
	int countByMallUserEntity_clientNameLikeAndClientIdLikeAndMallUserEntity_mobileLike(String username, String clientId, String moblie);

}
