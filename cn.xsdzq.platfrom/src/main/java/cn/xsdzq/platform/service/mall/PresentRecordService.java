package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentRecordEntity;


public interface PresentRecordService {

		List<PresentRecordEntity> getRecordEntities();
		//分页查询
		List<PresentRecordEntity> findByOrderByRecordTimeDesc(int pageNumber, int pageSize);
		int countAll();
		//
		List<PresentRecordEntity> findByPresentCardEntity_presentIdOrderByRecordTimeDesc(long presentId, int pageNumber, int pageSize);
		int countByPresentCardEntity_presentId(long presentId);
		
		List<PresentRecordEntity> findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(String clientId, int pageNumber, int pageSize);
		int countByMallUserEntity_clientIdLike(String clientId);
		
		List<PresentRecordEntity> findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(String mobile, int pageNumber, int pageSize);
		int countByMallUserEntity_mobileLike(String mobile);
		//
		List<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(long presentId, String clientId, int pageNumber, int pageSize);
		int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(long presentId,String clientId);
		
		List<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String mobile, int pageNumber, int pageSize);
		int countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(long presentId,String mobile);
		
		List<PresentRecordEntity> findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(String clientId, String mobile, int pageNumber, int pageSize);
		int countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(String clientId, String mobile);
		//
		
		List<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String clientId, String mobile, int pageNumber, int pageSize);
		int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(long presentId,String clientId, String mobile);
		
}
