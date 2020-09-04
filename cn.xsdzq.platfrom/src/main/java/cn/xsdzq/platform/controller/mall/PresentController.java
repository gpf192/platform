package cn.xsdzq.platform.controller.mall;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.service.mall.PresentService;
import cn.xsdzq.platform.util.GsonUtil;


@RestController
@RequestMapping(value = "/mall/present")
public class PresentController {

	private static final Logger logger = LoggerFactory.getLogger(PresentController.class);

	@Autowired
	private PresentService presentService;

	@PostMapping("/add")
	public Map<String, Object> addPresent(@RequestBody PresentEntity presentEntity) {

		logger.info(presentEntity.toString());
		presentService.addPresent(presentEntity);
		return GsonUtil.buildMap(0, "success", null);
	}
	
	@GetMapping("/all")
	public  Map<String, Object> getAllPresent(){
		
		List<PresentEntity> presentEntities = presentService.getPresentEntities();
		return GsonUtil.buildMap(0, "success", presentEntities);
	}
	
	

}
