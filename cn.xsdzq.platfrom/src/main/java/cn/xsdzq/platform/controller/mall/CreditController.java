package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Comparator;
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

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.mall.CreditDTO;
import cn.xsdzq.platform.model.mall.PresentCategory;
import cn.xsdzq.platform.service.mall.CreditService;
import cn.xsdzq.platform.service.mall.PresentCategoryService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.mall.CreditUtil;

@RestController
@RequestMapping(value = "/mall/credit")
public class CreditController {
	private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
	
	@Autowired
	@Qualifier("creditServiceImpl")
	private CreditService creditService;
	
	
	@PostMapping(value = "/add")
	public Map<String, Object> addCredit(@RequestBody CreditDTO creditDTO) {

		//logger.info(CreditDTO.toString());
		CreditEntity entity = CreditUtil.convertCreditEntityByDTO(creditDTO);
		creditService.addCredit(entity);
		return GsonUtil.buildMap(0, "success", null);
	}

	@GetMapping(value = "/all")
	public Map<String, Object> getCredit() {

		List<CreditEntity> entities = creditService.getCreditEntities();
		List<CreditDTO> cDtos = new ArrayList<CreditDTO>();
		for (CreditEntity entity : entities) {
			CreditDTO dto = CreditUtil.convertCreditDTOByEntity(entity);
			cDtos.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", cDtos);
	}
	

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCredit(@RequestBody CreditDTO creditDTO) {
		//有子分类不可删除
		//CreditEntity entity = CreditUtil.convertCreditEntityByDTO(creditDTO);
		creditService.deleteCredit(creditDTO.getId());
		return GsonUtil.buildMap(0, "success", null);
	}
}
