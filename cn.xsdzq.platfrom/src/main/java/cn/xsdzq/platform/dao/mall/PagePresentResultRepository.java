package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentResultEntity;

public interface PagePresentResultRepository extends PagingAndSortingRepository<PresentResultEntity, Long> {
	Page<PresentResultEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	//
	Page<PresentResultEntity> findByPresentCardEntity_presentIdOrderByRecordTimeDesc(long presentId, Pageable pageable);
	int countByPresentCardEntity_presentId(long presentId);
	
	Page<PresentResultEntity> findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(String clientId, Pageable pageable);
	int countByMallUserEntity_clientIdLike(String clientId);
	
	Page<PresentResultEntity> findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(String mobile, Pageable pageable);
	int countByMallUserEntity_mobileLike(String mobile);
	//
	Page<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(long presentId, String clientId, Pageable pageable);
	int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(long presentId,String clientId);
	
	Page<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String mobile, Pageable pageable);
	int countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(long presentId,String mobile);
	
	Page<PresentResultEntity> findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(String clientId, String mobile, Pageable pageable);
	int countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(String clientId, String mobile);
	//
	
	Page<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String clientId, String mobile, Pageable pageable);
	int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(long presentId,String clientId, String mobile);
	
}
