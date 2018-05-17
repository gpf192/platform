package cn.xsdzq.platform.model;

import java.util.List;

public class CategoryDTOList {
	private List<CategoryDTO> categoryDTOs;

	public CategoryDTOList() {
		super();
	}

	public CategoryDTOList(List<CategoryDTO> categoryDTOs) {
		super();
		this.categoryDTOs = categoryDTOs;
	}

	public List<CategoryDTO> getCategoryDTOs() {
		return categoryDTOs;
	}

	public void setCategoryDTOs(List<CategoryDTO> categoryDTOs) {
		this.categoryDTOs = categoryDTOs;
	}

}
