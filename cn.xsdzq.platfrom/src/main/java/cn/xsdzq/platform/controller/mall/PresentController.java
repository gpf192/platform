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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.PresentDTO;
import cn.xsdzq.platform.service.mall.PagePresentCardService;
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
	
	@Autowired
	@Qualifier("pagePresentCardServiceImpl")
	private PagePresentCardService pagePresentCardService;
	
	
	@PostMapping("/add")
	public Map<String, Object> addPresent(@RequestBody PresentDTO dto) {
		PresentEntity entity = PresentUtil.convertPresentEntityByDto(dto);
		PresentCategoryEntity c = presentCategoryService.findById(dto.getCategoryId());
		entity.setPresentCategory(c);
		//entity.setCreatetime(new Date());
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
		PresentDTO all = new PresentDTO();
		all.setName("全部");
		all.setCode("all");
		cDtos.add(all);
		return GsonUtil.buildMap(0, "success", cDtos);
		
	}
	
	@GetMapping(value = "/getAllPresentPage")
	public Map<String, Object> getAllPage(HttpServletRequest request,@RequestParam String name,@RequestParam String categoryCode,@RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		int num = MethodUtil.getMethodNum(name, categoryCode);
		List<PresentEntity> entities =null;
		if(num == 1) {
			//全量查找
			entities = presentService.findByOrderByCreatetimeDesc(pageNumber, pageSize);
			sum = presentService.countAll();
		}
		if(num == 2) {
			//ab查找
			entities = presentService.findByNameAndPresentCategory_nameOrderByCreatetimeDesc(name, categoryCode, pageNumber, pageSize);
			sum = presentService.countByNameAndPresentCategory_name(name, categoryCode);
		}
		if(num == 3) {
			//a查找
			entities = presentService.findByNameOrderByCreatetimeDesc(categoryCode, pageNumber, pageSize);
			sum = presentService.countByName(categoryCode);
		}
		if(num == 4) {
			//b查找
			entities = presentService.findByPresentCategory_nameOrderByCreatetimeDesc(categoryCode, pageNumber, pageSize);
			sum = presentService.countByPresentCategory_name(categoryCode);
		}
		List<PresentDTO> cDtos = new ArrayList<PresentDTO>();
		for (PresentEntity entity : entities) {
			//查询每个present下面有多少个卡券,总数=已兑换（上架状态）+库存（未兑换且上架状态）+下架（未兑换状态）
			//已兑换
			int convertNumber = pagePresentCardService.countByPresentIdAndConvertStatusAndCardStatus(entity.getId(),1,1);//已兑换并且上架，已兑换的只有上架状态
			//库存
			int storeUnused = pagePresentCardService.countByPresentIdAndConvertStatusAndCardStatus(entity.getId(), 0,1);//剩余库存
			//已下架
			int xiajia = pagePresentCardService.countByPresentIdAndCardStatus(entity.getId(),0 );
			PresentDTO dto = PresentUtil.convertPresentDTOByEntity(entity);
			dto.setStoreNumber(convertNumber+storeUnused+xiajia);//总库存实时计算
			dto.setConvertNumber(convertNumber);
			dto.setStoreUnused(storeUnused);
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
