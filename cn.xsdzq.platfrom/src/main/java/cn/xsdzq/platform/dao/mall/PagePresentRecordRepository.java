package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentRecordEntity;
import cn.xsdzq.platform.entity.mall.PresentResultEntity;

public interface PagePresentRecordRepository extends PagingAndSortingRepository<PresentRecordEntity, Long> {
	Page<PresentRecordEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	//
	Page<PresentRecordEntity> findByPresentCardEntity_presentIdOrderByRecordTimeDesc(long presentId, Pageable pageable);
	int countByPresentCardEntity_presentId(long presentId);
	
	Page<PresentRecordEntity> findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(String clientId, Pageable pageable);
	int countByMallUserEntity_clientIdLike(String clientId);
	
	Page<PresentRecordEntity> findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(String mobile, Pageable pageable);
	int countByMallUserEntity_mobileLike(String mobile);
	//
	Page<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(long presentId, String clientId, Pageable pageable);
	int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(long presentId,String clientId);
	
	Page<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String mobile, Pageable pageable);
	int countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(long presentId,String mobile);
	
	Page<PresentRecordEntity> findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(String clientId, String mobile, Pageable pageable);
	int countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(String clientId, String mobile);
	//
	
	Page<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String clientId, String mobile, Pageable pageable);
	int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(long presentId,String clientId, String mobile);
	
}
