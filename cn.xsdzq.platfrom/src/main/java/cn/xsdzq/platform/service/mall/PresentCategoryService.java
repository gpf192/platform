package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.model.mall.PresentCategory;


public interface PresentCategoryService {

	public void addPresentCategory(PresentCategory presentCategory);

	public List<PresentCategoryEntity> getCategoryEntities();
	public void deletePresentCategory(PresentCategory presentCategory);
	public PresentCategoryEntity findById(long id);

}
