package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.CategoryEntity;

@Repository
public interface CategoryRepository {

	CategoryEntity findCategoryById(long id);

	public List<CategoryEntity> findAll();

	void addCategory(CategoryEntity category);

	void modifyCategory(CategoryEntity category);

	void deleteCategory(long id);

	void sortCategory(List<CategoryEntity> categoryEntities);

}
