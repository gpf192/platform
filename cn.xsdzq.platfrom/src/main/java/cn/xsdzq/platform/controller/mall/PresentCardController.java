package cn.xsdzq.platform.controller.mall;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.service.mall.PagePresentCardService;
import cn.xsdzq.platform.service.mall.PresentCardService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;


@RestController
@RequestMapping(value = "/mall/card")
public class PresentCardController {
	@Autowired
	private PresentCardService presentCardService;

	@Autowired
	@Qualifier("pagePresentCardServiceImpl")
	private PagePresentCardService pagePresentCardService;
	
	@PostMapping(value = "/add")
	public Map<String, Object> addCard(@RequestBody PresentCardEntity presentCardEntity) {

		presentCardService.addPresentCard(presentCardEntity);
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
		//return GsonUtil.buildMap(0, "success", presentCardEntities);
	}
}
