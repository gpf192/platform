package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.model.mall.PresentCategoryDTO;


public interface PresentCategoryService {

	public void addPresentCategory(PresentCategoryEntity presentCategoryEntity);

	public List<PresentCategoryEntity> getCategoryEntities();
	public void deletePresentCategory(PresentCategoryDTO presentCategory);
	public PresentCategoryEntity findById(long id);
	
	//分页查询
	List<PresentCategoryEntity> findByOrderByCreatetimeDesc(int pageNumber, int pageSize);
	int countAll();

}
