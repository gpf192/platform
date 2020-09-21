package cn.xsdzq.platform.service.mall;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.xsdzq.platform.entity.mall.PresentResultEntity;


public interface PresentResultService {

	public List<PresentResultEntity> getResultEntities();
	//分页查询
	List<PresentResultEntity> findByOrderByRecordTimeDesc(int pageNumber, int pageSize);
	int countAll();
	//
	List<PresentResultEntity> findByPresentCardEntity_presentIdOrderByRecordTimeDesc(long presentId, int pageNumber, int pageSize);
	int countByPresentCardEntity_presentId(long presentId);
	
	List<PresentResultEntity> findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(String clientId, int pageNumber, int pageSize);
	int countByMallUserEntity_clientIdLike(String clientId);
	
	List<PresentResultEntity> findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(String mobile, int pageNumber, int pageSize);
	int countByMallUserEntity_mobileLike(String mobile);
	//
	List<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(long presentId, String clientId, int pageNumber, int pageSize);
	int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(long presentId,String clientId);
	
	List<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String mobile, int pageNumber, int pageSize);
	int countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(long presentId,String mobile);
	
	List<PresentResultEntity> findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(String clientId, String mobile, int pageNumber, int pageSize);
	int countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(String clientId, String mobile);
	//
	
	List<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(long presentId, String clientId, String mobile, int pageNumber, int pageSize);
	int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(long presentId,String clientId, String mobile);
	

}
