package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.PresentCategoryDTO;
import cn.xsdzq.platform.model.mall.PresentDTO;
import cn.xsdzq.platform.service.mall.PresentCategoryService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;


@RestController
@RequestMapping(value = "/mall/categoryy")
public class PresentCategoryController {

	public static Logger logger = LoggerFactory.getLogger(PresentCategoryController.class);

	@Autowired
	@Qualifier("presentCategoryServiceImpl")
	private PresentCategoryService presentCategoryService;

	@PostMapping(value = "/add")
	public Map<String, Object> addCategory(@RequestBody PresentCategoryDTO dto) {

		//logger.info(presentCategory.toString());
		PresentCategoryEntity entity = PresentUtil.convertPresentCategoryEntityByDto(dto);
		//presentCategoryService.addPresentCategory(entity);
		if(dto.getIsNew()==0) {
			//新增			
			presentCategoryService.addPresentCategory(entity);
		}else {
			//更新
			PresentCategoryEntity t = presentCategoryService.findById(entity.getId());
			entity.setCreatetime(t.getCreatetime());
			presentCategoryService.addPresentCategory(entity);
		}
		
		return GsonUtil.buildMap(0, "success", null);
	}


	@GetMapping(value = "/all")
	public Map<String, Object> getPresentCategorys() {
					
		List<PresentCategoryEntity> list = presentCategoryService.getCategoryEntities();
		List<PresentCategoryDTO> cDtos = new ArrayList<PresentCategoryDTO>();
		for (PresentCategoryEntity category : list) {
			PresentCategoryDTO dto = PresentUtil.convertPresentCategoryDTOByEntity(category);
			cDtos.add(dto);
		}
		PresentCategoryDTO all = new PresentCategoryDTO();
		all.setId(0);
		all.setName("全部");
		all.setCode("all");
		cDtos.add(all);
		return GsonUtil.buildMap(0, "success", cDtos);
	
	}
	
	@GetMapping(value = "/getAllPage")
	public Map<String, Object> getAllPage(HttpServletRequest request,@RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;			
		List<PresentCategoryEntity> list = presentCategoryService.findByOrderByCreatetimeDesc(pageNumber, pageSize);
		List<PresentCategoryDTO> cDtos = new ArrayList<PresentCategoryDTO>();
		for (PresentCategoryEntity category : list) {
			PresentCategoryDTO dto = PresentUtil.convertPresentCategoryDTOByEntity(category);
			cDtos.add(dto);
		}
		sum = presentCategoryService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", cDtos,pagination);
	
	}
	

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCategory(@RequestBody PresentCategoryDTO presentCategory) {
		//有子分类不可删除
		presentCategoryService.deletePresentCategory(presentCategory);
		return GsonUtil.buildMap(0, "success", null);
	}

}
