package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentEntity;

public interface PagePresentRepository extends  PagingAndSortingRepository<PresentEntity, Long> {
	Page<PresentEntity> findByNameNotOrderByCreatetimeDesc(String all,Pageable pageable);
	int countByNameNot(String all);
	
	Page<PresentEntity> findByNameLikeAndNameNotOrderByCreatetimeDesc(String  name , String all,Pageable pageable);
	int countByNameLikeAndNameNot(String name,String all);
	
	Page<PresentEntity> findByPresentCategory_codeLikeAndNameNotOrderByCreatetimeDesc(String  categoryName ,String all, Pageable pageable);
	int countByPresentCategory_codeLikeAndNameNot(String categoryName,String all);
	
	Page<PresentEntity> findByNameLikeAndPresentCategory_codeLikeAndNameNotOrderByCreatetimeDesc(String  name ,String categoryName,String all,Pageable pageable);
	int countByNameLikeAndPresentCategory_codeLikeAndNameNot(String name,String categoryName,String all);
}
