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
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditImportRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditUserTotalEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.CreditDTO;
import cn.xsdzq.platform.model.mall.CreditImportRecordDTO;
import cn.xsdzq.platform.model.mall.CreditUserTotalDTO;
import cn.xsdzq.platform.service.mall.CreditImportRecordService;
import cn.xsdzq.platform.service.mall.CreditService;
import cn.xsdzq.platform.service.mall.CreditUserTotalService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.mall.CreditUtil;

@RestController
@RequestMapping(value = "/mall/credit")
public class CreditController {
	private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
	
	@Autowired
	@Qualifier("creditServiceImpl")
	private CreditService creditService;
	
	@Autowired
	@Qualifier("creditUserTotalServiceImpl")
	private CreditUserTotalService creditUserTotalService;
	
	@Autowired
	@Qualifier("creditImportRecordServiceImpl")
	private CreditImportRecordService creditImportRecordService;
	
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
	
	
	//查询用户总积分数
	@GetMapping(value = "/getUserCreditTotal")
	public Map<String, Object> getUserCreditTotal(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<CreditUserTotalEntity> presentCardEntities = creditUserTotalService.findByOrderByTotalDesc(pageNumber, pageSize);
		List<CreditUserTotalDTO> dtos = new ArrayList<CreditUserTotalDTO>();
		for (CreditUserTotalEntity entity : presentCardEntities) {
			CreditUserTotalDTO dto = CreditUtil.convertCreditUserTotalDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = creditUserTotalService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
	
	//查询用户积分导入明细
	@GetMapping(value = "/getCreditImportRecord")
	public Map<String, Object> getCreditImportRecord(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<CreditImportRecordEntity> presentCardEntities = creditImportRecordService.findByOrderByBeginDateDesc(pageNumber, pageSize);
		List<CreditImportRecordDTO> dtos = new ArrayList<CreditImportRecordDTO>();
		for (CreditImportRecordEntity entity : presentCardEntities) {
			CreditImportRecordDTO dto = CreditUtil.convertCreditImportRecordDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = creditImportRecordService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
}
