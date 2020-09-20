package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
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

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.PresentDTO;
import cn.xsdzq.platform.service.mall.PresentCategoryService;
import cn.xsdzq.platform.service.mall.PresentService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;


@RestController
@RequestMapping(value = "/mall/present")
public class PresentController {

	private static final Logger logger = LoggerFactory.getLogger(PresentController.class);

	@Autowired
	@Qualifier("presentServiceImpl")
	private PresentService presentService;

	@Autowired
	@Qualifier("presentCategoryServiceImpl")
	private PresentCategoryService presentCategoryService;
	
	@PostMapping("/add")
	public Map<String, Object> addPresent(@RequestBody PresentDTO presentEntity) {
		
		PresentEntity entity = PresentUtil.convertPresentEntityByDto(presentEntity);
		System.out.println(entity.toString());
		PresentCategoryEntity c = presentCategoryService.findById(presentEntity.getCategoryId());
		entity.setPresentCategory(c);
		entity.setCreatetime(new Date());
		presentService.addPresent(entity);
		return GsonUtil.buildMap(0, "success", null);
	}
	
	@GetMapping("/all")
	public  Map<String, Object> getAllPresent(){
		
		List<PresentEntity> list = presentService.getPresentEntities();
		List<PresentDTO> cDtos = new ArrayList<PresentDTO>();
		for (PresentEntity entity : list) {
			PresentDTO dto = PresentUtil.convertPresentDTOByEntity(entity);
			cDtos.add(dto);
		}
		
		return GsonUtil.buildMap(0, "success", cDtos);
		
	}
	
	@GetMapping(value = "/getAllPresentPage")
	public Map<String, Object> getAllPage(HttpServletRequest request,@RequestParam String name,@RequestParam String categoryName,@RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		int num = MethodUtil.getMethodNum(name, categoryName);
		List<PresentEntity> entities =null;
		if(num == 1) {
			//全量查找
			entities = presentService.findByOrderByCreatetimeDesc(pageNumber, pageSize);
			sum = presentService.countAll();
		}
		if(num == 2) {
			//ab查找
			entities = presentService.findByNameAndPresentCategory_nameOrderByCreatetimeDesc(name, categoryName, pageNumber, pageSize);
			sum = presentService.countByNameAndPresentCategory_name(name, categoryName);
		}
		if(num == 3) {
			//a查找
			entities = presentService.findByNameOrderByCreatetimeDesc(categoryName, pageNumber, pageSize);
			sum = presentService.countByName(categoryName);
		}
		if(num == 4) {
			//b查找
			entities = presentService.findByPresentCategory_nameOrderByCreatetimeDesc(categoryName, pageNumber, pageSize);
			sum = presentService.countByPresentCategory_name(categoryName);
		}
		List<PresentDTO> cDtos = new ArrayList<PresentDTO>();
		for (PresentEntity entity : entities) {
			PresentDTO dto = PresentUtil.convertPresentDTOByEntity(entity);
			cDtos.add(dto);
		}
		
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", cDtos,pagination);
	
	}
	
	
	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePresent(HttpServletRequest request,  @RequestBody PresentDTO dto) {		
		presentService.deletePresent(dto.getId());
		
		return GsonUtil.buildMap(0, "success", null);
	}
	

}
