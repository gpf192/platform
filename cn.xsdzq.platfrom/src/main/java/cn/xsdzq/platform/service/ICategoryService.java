package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.CategoryEntity;

public interface ICategoryService {

	CategoryEntity getCategoryById(long id);

	List<CategoryEntity> findAll();

	void addCategory(CategoryEntity categoryEntity);

	void modifyCategory(CategoryEntity categoryEntity);

	void deleteCategory(long id);

	void sortCategory(List<CategoryEntity> categoryEntities);

}
