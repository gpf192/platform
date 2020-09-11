package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
import cn.xsdzq.platform.model.mall.PresentCategory;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.service.mall.PagePresentCardService;
import cn.xsdzq.platform.service.mall.PresentCardService;
import cn.xsdzq.platform.service.mall.PresentService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;


@RestController
@RequestMapping(value = "/mall/card")
public class PresentCardController {
	@Autowired
	private PresentCardService presentCardService;

	@Autowired
	@Qualifier("presentServiceImpl")
	private PresentService presentService;
	
	@Autowired
	@Qualifier("pagePresentCardServiceImpl")
	private PagePresentCardService pagePresentCardService;
	
	@PostMapping(value = "/add")
	public Map<String, Object> addCard(@RequestBody PresentCardDTO dto) {
		PresentCardEntity entity = PresentUtil.convertPresentCardEntityByDTO(dto);
		PresentEntity p = presentService.findById(dto.getPresentId());
		entity.setPresent(p);
		presentCardService.addPresentCard(entity);
		return GsonUtil.buildMap(0, "success", null);
	}

	@GetMapping(value = "/all")
	public Map<String, Object> getPresentCategorys(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<PresentCardEntity> presentCardEntities = pagePresentCardService.findByOrderByCreateDateDesc(pageNumber, pageSize);
		List<PresentCardDTO> dtos = new ArrayList<PresentCardDTO>();
		for (PresentCardEntity entity : presentCardEntities) {
			PresentCardDTO dto = PresentUtil.convertPresentCardDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = pagePresentCardService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
		//return GsonUtil.buildMap(0, "success", presentCardEntities);
	}

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePresentCard(HttpServletRequest request,  @RequestBody PresentCardDTO dto) {		
		presentCardService.deletePresentCard(dto);
		
		return GsonUtil.buildMap(0, "success", null);
	}
	
	
	@GetMapping(value = "/presentResult")
	public Map<String, Object> getPresentResult(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<PresentCardEntity> presentCardEntities = pagePresentCardService.findByOrderByCreateDateDesc(pageNumber, pageSize);
		List<PresentCardDTO> dtos = new ArrayList<PresentCardDTO>();
		for (PresentCardEntity entity : presentCardEntities) {
			PresentCardDTO dto = PresentUtil.convertPresentCardDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = pagePresentCardService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
}
