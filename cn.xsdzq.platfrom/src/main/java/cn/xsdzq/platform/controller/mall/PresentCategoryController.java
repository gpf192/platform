package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.model.mall.PresentCategory;
import cn.xsdzq.platform.service.mall.PresentCategoryService;
import cn.xsdzq.platform.util.GsonUtil;


@RestController
@RequestMapping(value = "/mall/category")
public class PresentCategoryController {

	public static Logger logger = LoggerFactory.getLogger(PresentCategoryController.class);

	@Autowired
	@Qualifier("presentCategoryServiceImpl")
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
