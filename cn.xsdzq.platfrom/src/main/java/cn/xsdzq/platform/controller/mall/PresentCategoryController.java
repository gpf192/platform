package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.model.PresentCategory;
import com.xsdzq.mall.service.PresentCategoryService;
import com.xsdzq.mall.util.GsonUtil;

@RestController
@RequestMapping(value = "/mall/category")
public class PresentCategoryController {

	public static Logger logger = LoggerFactory.getLogger(PresentCategoryController.class);

	@Autowired
	private PresentCategoryService presentCategoryService;

	@PostMapping(value = "/add")
	public Map<String, Object> addCategory(@RequestBody PresentCategory presentCategory) {

		logger.info(presentCategory.toString());
		presentCategoryService.addPresentCategory(presentCategory);
		return GsonUtil.buildMap(0, "success", null);
	}

	@GetMapping(value = "/all")
	public Map<String, Object> getPresentCategorys() {

		List<PresentCategoryEntity> categoryEntities = presentCategoryService.getCategoryEntities();
		return GsonUtil.buildMap(0, "success", categoryEntities);
	}
	

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCategory(@RequestBody PresentCategory presentCategory) {
		//有子分类不可删除
		presentCategoryService.deletePresentCategory(presentCategory);
		return GsonUtil.buildMap(0, "success", null);
	}

}
