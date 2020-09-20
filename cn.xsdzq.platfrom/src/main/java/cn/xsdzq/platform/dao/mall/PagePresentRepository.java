package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.PresentEntity;

public interface PagePresentRepository extends  PagingAndSortingRepository<PresentEntity, Long> {
	Page<PresentEntity> findByOrderByCreatetimeDesc(Pageable pageable);
	
	Page<PresentEntity> findByNameOrderByCreatetimeDesc(String  name , Pageable pageable);
	int countByName(String name);
	
	Page<PresentEntity> findByPresentCategory_nameOrderByCreatetimeDesc(String  categoryName , Pageable pageable);
	int countByPresentCategory_name(String categoryName);
	
	Page<PresentEntity> findByNameAndPresentCategory_nameOrderByCreatetimeDesc(String  name ,String categoryName, Pageable pageable);
	int countByNameAndPresentCategory_name(String name,String categoryName);
}
