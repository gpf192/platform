package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.util.GsonUtil;

@Controller
@RequestMapping("/category")
public class CategoryController {

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
			CategoryDTO dto = new CategoryDTO();
			dto.setId(category.getId());
			dto.setTitle(category.getTitle());
			dto.setExp(category.getExp());
			cDtos.add(dto);

		}
		return GsonUtil.buildMap(0, "ok", cDtos);
	}

	@RequestMapping(value = "/addCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addCategory(HttpServletRequest request, @RequestBody CategoryDTO dto) {
		System.out.println(dto.getTitle());
		CategoryEntity category = new CategoryEntity();
		String title = dto.getTitle();
		category.setTitle(title);
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
		System.out.println(dto.getTitle());
		categoryService.modifyCategory(category);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteCategory/{param}", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCategory(HttpServletRequest request, @PathVariable String param) {
		long id = Long.parseLong(param);
		if (id > 0) {
			categoryService.deleteCategory(id);
			return GsonUtil.buildMap(0, "ok", null);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

}
