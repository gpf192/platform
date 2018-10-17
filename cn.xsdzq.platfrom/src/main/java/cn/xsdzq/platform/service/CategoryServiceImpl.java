package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.CategoryRepository;
import cn.xsdzq.platform.entity.CategoryEntity;

@Service(value = "categoryServiceImpl")
@Transactional(readOnly = true)
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryEntity getCategoryById(long id) {
		// TODO Auto-generated method stub
		CategoryEntity categoryEntity = categoryRepository.findCategoryById(id);
		return categoryEntity;
	}
	
	@Override
	public List<CategoryEntity> findAll() {
		// TODO Auto-generated method stub
		List<CategoryEntity> list = categoryRepository.findAll();
		return list;
	}
	
	@Override
	public List<CategoryEntity> findDisplayCategory() {
		// TODO Auto-generated method stub
		List<CategoryEntity> displayList = categoryRepository.getDisplayCategory();
		return displayList;
	}

	@Override
	@Transactional
	public void addCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		categoryRepository.addCategory(categoryEntity);
	}

	@Override
	@Transactional
	public void modifyCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		categoryRepository.modifyCategory(categoryEntity);
	}

	@Override
	@Transactional
	public void deleteCategory(long id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteCategory(id);
	}

	@Override
	@Transactional
	public void sortCategory(List<CategoryEntity> categoryEntities) {
		// TODO Auto-generated method stub
		// categoryRepository.sortCategory(categoryEntities);
		for (CategoryEntity category : categoryEntities) {
			categoryRepository.modifyCategory(category);
		}
	}



}
