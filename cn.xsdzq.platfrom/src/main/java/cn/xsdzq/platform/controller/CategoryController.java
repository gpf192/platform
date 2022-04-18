package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.CategoryDTOList;
import cn.xsdzq.platform.model.IdDTO;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.GsonUtil;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private static Logger logger = LogManager.getLogger(CategoryController.class);

	@Autowired
	@Qualifier("categoryServiceImpl")
	private ICategoryService categoryService;

	@RequestMapping(value = "/getCategory", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public CategoryDTO getCategory() {
		CategoryEntity category = categoryService.getCategoryById(1);
		System.out.println(category.getTitle());
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setTitle(category.getTitle());
		return categoryDTO;
	}

	@RequestMapping(value = "/getAll", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllCategory() {
		List<CategoryEntity> list = categoryService.findAll();
		List<CategoryDTO> cDtos = new ArrayList<CategoryDTO>();
		for (CategoryEntity category : list) {
			CategoryDTO dto = CategoryUtil.convertCategoryDTOByCategoryEntity(category);
			cDtos.add(dto);
		}
		cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}
	@RequestMapping(value = "/getAllExcept", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllCategoryExcept() {
		List<CategoryEntity> list = categoryService.findAllExcept();
		List<CategoryDTO> cDtos = new ArrayList<CategoryDTO>();
		for (CategoryEntity category : list) {
			CategoryDTO dto = CategoryUtil.convertCategoryDTOByCategoryEntity(category);
			cDtos.add(dto);
		}
		cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}
	
	@RequestMapping(value = "/getDisplayCategories", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getDisplayCategories() {
		List<CategoryEntity> list = categoryService.findDisplayCategory();
		List<CategoryDTO> cDtos = new ArrayList<CategoryDTO>();
		for (CategoryEntity category : list) {
			CategoryDTO dto = CategoryUtil.convertCategoryDTOByCategoryEntity(category);
			cDtos.add(dto);
		}
		cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}

	@RequestMapping(value = "/addCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addCategory(HttpServletRequest request, @RequestBody CategoryDTO categoryDTO) {
		System.out.println(categoryDTO.getTitle());
		CategoryEntity category = CategoryUtil.convertCategoryEntityByCategoryDTO(categoryDTO);
		categoryService.addCategory(category);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyCategory(HttpServletRequest request, @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO dto = categoryDTO;
		CategoryEntity category = new CategoryEntity();
		category.setId(dto.getId());
		category.setTitle(dto.getTitle());
		category.setExp(dto.getExp());
		category.setImage(dto.getImage());
		category.setDisplayFlag(dto.getDisplayFlag());
		categoryService.modifyCategory(category);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCategory(@RequestBody IdDTO idDTO) {
		long id = idDTO.getId();
		logger.info("id: " + id);
		if (id > 0) {
			categoryService.deleteCategory(id);
			return GsonUtil.buildMap(0, "ok", null);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

	@RequestMapping(value = "/sortCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> sort(@RequestBody CategoryDTOList categoryDTOList) {
		List<CategoryEntity> categoryEntities = new ArrayList<>();
		for (CategoryDTO categoryDTO : categoryDTOList.getCategoryDTOs()) {
			categoryEntities.add(CategoryUtil.convertCategoryEntityByCategoryDTO(categoryDTO));
		}
		categoryService.sortCategory(categoryEntities);
		return GsonUtil.buildMap(0, "ok", null);
	}

}
