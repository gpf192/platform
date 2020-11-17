package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;

public interface PagePresentCardService {
	List<PresentCardEntity> findByOrderByCreateDateDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<PresentCardEntity> findByCardIdLikeOrderByCreateDateDesc(String cardId, int pageNumber, int pageSize);
	int countByCardIdLike(String cardId);
	
	List<PresentCardEntity> findByPresentIdOrderByCreateDateDesc(long presentId, int pageNumber, int pageSize);
	int countByPresentId(long presentId);
	
	List<PresentCardEntity> findByCardIdLikeAndPresentIdOrderByCreateDateDesc(String cardId, long presentId,int pageNumber, int pageSize);
	int countByCardIdLikeAndPresentId(String cardId,long presentId);
	
	//查询库存
		public int countByPresentIdAndConvertStatusAndCardStatus(long PresentId,int convertStatus, int cardStatus);
		//查询已下架
		public int countByPresentIdAndCardStatus(long presentId,int cardStatus);
}
