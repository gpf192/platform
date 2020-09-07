package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;

public interface PagePresentCardRepository extends PagingAndSortingRepository<PresentCardEntity, Long> {
	Page<PresentCardEntity> findByOrderByCreateDateDesc(Pageable pageable);
	
	Page<PresentCardEntity> findByCardIdOrderByCreateDateDesc(String cardId, Pageable pageable);
	int countByCardId(String cardId);

	//Page<PresentCardEntity> findByOrderByCreateDateDesc(Pageable pageable);
	//int countInfoEntityByCreatedBy(String username);

}
