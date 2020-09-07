package cn.xsdzq.platform.controller.mall;

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
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.mall.PresentDTO;
import cn.xsdzq.platform.service.mall.PresentCategoryService;
import cn.xsdzq.platform.service.mall.PresentService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;


@RestController
@RequestMapping(value = "/mall/present")
public class PresentController {

	private static final Logger logger = LoggerFactory.getLogger(PresentController.class);

	@Autowired
	private PresentService presentService;

	@Autowired
	@Qualifier("presentCategoryServiceImpl")
	private PresentCategoryService presentCategoryService;
	
	@PostMapping("/add")
	public Map<String, Object> addPresent(@RequestBody PresentDTO presentEntity) {
		//System.out.println("&&&&&&&__________________________=+=================");

//System.out.println(presentEntity.toString());
		//logger.info(presentEntity.toString());
		PresentEntity entity = PresentUtil.convertPresentEntityByDto(presentEntity);
		System.out.println("&&&&&&&__________________________=+=================");
		System.out.println(entity.toString());
		PresentCategoryEntity c = presentCategoryService.findById(presentEntity.getCategoryId());
		entity.setPresentCategory(c);
		presentService.addPresent(entity);
		return GsonUtil.buildMap(0, "success", null);
	}
	
	@GetMapping("/all")
	public  Map<String, Object> getAllPresent(){
		
		List<PresentEntity> presentEntities = presentService.getPresentEntities();
		return GsonUtil.buildMap(0, "success", presentEntities);
	}
	
	

}
