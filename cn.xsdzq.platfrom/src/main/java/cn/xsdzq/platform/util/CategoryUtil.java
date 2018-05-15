package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.model.CategoryDTO;

public class CategoryUtil {
	public static CategoryEntity convertCategoryEntityByCategoryDTO(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(categoryDTO.getId());
		categoryEntity.setTitle(categoryDTO.getTitle());
		categoryEntity.setImage(categoryDTO.getImage());
		categoryEntity.setCategorysort(categoryDTO.getCategorysort());
		categoryEntity.setExp(categoryDTO.getExp());
		return categoryEntity;
	}

	public static CategoryDTO convertCategoryDTOByCategoryEntity(CategoryEntity categoryEntity) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(categoryEntity.getId());
		categoryDTO.setTitle(categoryEntity.getTitle());
		categoryDTO.setImage(categoryEntity.getImage());
		categoryDTO.setCategorysort(categoryEntity.getCategorysort());
		categoryDTO.setExp(categoryEntity.getExp());
		return categoryDTO;
	}

}
