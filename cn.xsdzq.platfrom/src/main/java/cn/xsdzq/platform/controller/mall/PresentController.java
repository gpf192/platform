package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
import cn.xsdzq.platform.model.mall.PresentCategory;
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
	
	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePresent(HttpServletRequest request,  @RequestBody PresentDTO dto) {		
		presentService.deletePresent(dto.getId());
		
		return GsonUtil.buildMap(0, "success", null);
	}
	

}
