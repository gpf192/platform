package cn.xsdzq.platform.dao.mall;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;

public interface PagePresentCardRepository extends PagingAndSortingRepository<PresentCardEntity, Long> {
	Page<PresentCardEntity> findByOrderByCreateDateDesc(Pageable pageable);
	


	Page<PresentCardEntity> findByCardIdLikeOrderByCreateDateDesc(String cardId, Pageable pageable);
	int countByCardIdLike(String cardId);
	
	Page<PresentCardEntity> findByPresentIdOrderByCreateDateDesc(long presentId, Pageable pageable);
	int countByPresentId(long presentId);
	
	Page<PresentCardEntity> findByCardIdLikeAndPresentIdOrderByCreateDateDesc(String cardId, long presentId,Pageable pageable);
	int countByCardIdLikeAndPresentId(String cardId,long presentId);
	//查询已兑换、未兑换状态数量，且上架状态
	int countByPresentIdAndConvertStatusAndCardStatus(long presentId, int convertStatus, int cardStatus);
	//查询已下架
	int countByPresentIdAndCardStatus(long presentId, int cardStatus);
	

}
