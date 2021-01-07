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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditProductViewEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.CRMCreditApiMsgDTO;
import cn.xsdzq.platform.model.mall.CRMCreditProductViewDTO;
import cn.xsdzq.platform.model.mall.CRMCreditRecordDTO;
import cn.xsdzq.platform.service.mall.CRMCreditProductService;
import cn.xsdzq.platform.service.mall.CRMCreditRecordService;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.mall.CreditUtil;

@RestController
@RequestMapping(value = "/mall/crm")
public class CRMCreditController {
	private static final Logger logger = LoggerFactory.getLogger(CRMCreditController.class);
	
	@Autowired
	@Qualifier("crmCreditRecordServiceImpl")
	private CRMCreditRecordService crmCreditRecordService;
	
	@Autowired
	private MallUserService mallUserService;
	
	@Autowired
	private CRMCreditProductService crmCreditProductService;
	
	//CRM分页查询用户积分明细
		@GetMapping(value = "/getCrmCreditRecord")
		public Map<String, Object> getCrmCreditRecord(HttpServletRequest request, @RequestParam String serialNum,
				@RequestParam String clientId,
				@RequestParam String itemCode,@RequestParam int pageNumber,@RequestParam int pageSize) {
			System.out.println(serialNum+"--"+clientId+"--"+itemCode);
			int sum = 0 ;		
			int num = MethodUtil.getEmpMethodNum(serialNum,clientId,itemCode);
			List<CRMCreditRecordEntity> entities = null;
			if(num == 1) {
				//全量查找
				entities = crmCreditRecordService.findByOrderByBeginDateDesc(pageNumber, pageSize);
				sum = crmCreditRecordService.countAll();
			}
			if(num == 2) {
				//abc
				entities = crmCreditRecordService.findByClientIdLikeAndSerialNumLikeAndItemCodeLikeOrderByBeginDateDesc(clientId, serialNum, itemCode, pageNumber, pageSize);
				sum = crmCreditRecordService.countByClientIdLikeAndSerialNumLikeAndItemCodeLike(clientId, serialNum, itemCode);
			}
			if(num == 3) {
				//a
				entities = crmCreditRecordService.findBySerialNumLikeOrderByBeginDateDesc(serialNum, pageNumber, pageSize);
				sum = crmCreditRecordService.countBySerialNumLike(serialNum);
				
			}
			if(num == 4) {
				//b
				entities = crmCreditRecordService.findByClientIdLikeOrderByBeginDateDesc(clientId, pageNumber, pageSize);
				sum = crmCreditRecordService.countByClientIdLike(clientId);
				
			}
			if(num == 5) {
				//c
				entities = crmCreditRecordService.findByItemCodeLikeOrderByBeginDateDesc(serialNum, pageNumber, pageSize);
				sum = crmCreditRecordService.countByItemCodeLike(itemCode);
			
			}
			if(num == 6) {
				//ab
				entities = crmCreditRecordService.findByClientIdLikeAndSerialNumLikeOrderByBeginDateDesc(clientId, serialNum, pageNumber, pageSize);
				sum = crmCreditRecordService.countByClientIdLikeAndSerialNumLike(clientId, serialNum);
			
			}
			if(num == 7) {
				//ac
				entities = crmCreditRecordService.findByItemCodeLikeAndSerialNumLikeOrderByBeginDateDesc(itemCode, serialNum, pageNumber, pageSize);
				sum = crmCreditRecordService.countByItemCodeLikeAndSerialNumLike(itemCode, serialNum);
			
			}
			if(num == 8) {
				//bc
				entities = crmCreditRecordService.findByClientIdLikeAndItemCodeLikeOrderByBeginDateDesc(clientId, itemCode, pageNumber, pageSize);
				sum = crmCreditRecordService.countByClientIdLikeAndItemCodeLike(clientId, itemCode);
				
			}
			
			List<CRMCreditRecordDTO> dtos = new ArrayList<CRMCreditRecordDTO>();
			for (CRMCreditRecordEntity entity : entities) {
				CRMCreditRecordDTO dto = CreditUtil.convertCRMCreditDTOByEntity(entity);
				dtos.add(dto);
			}
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", dtos,pagination);
		}
		//convertCRMCreditMsgDTOByEntity
		
		@GetMapping(value = "/getCrmCreditApiMsg")
		public Map<String, Object> getCrmCreditApiMsg(HttpServletRequest request, @RequestParam String serialNum,
				@RequestParam int pageNumber,@RequestParam int pageSize) {
			
			int sum = 0 ;		
			List<CRMCreditApiErrorMsgEntity> entities = null;
			if("".equals(serialNum)) {
				//全量查找
				entities = crmCreditRecordService.findMsgByOrderByRecordTimeDesc(pageNumber, pageSize);
				sum = crmCreditRecordService.countMsgAll();
			}else{
				
				entities = crmCreditRecordService.findMsgBySerialNumLikeOrderByRecordTimeDesc(serialNum, pageNumber, pageSize);
				sum = crmCreditRecordService.countMsgBySerialNumLike(serialNum);
			}			
			
			List<CRMCreditApiMsgDTO> dtos = new ArrayList<CRMCreditApiMsgDTO>();
			for (CRMCreditApiErrorMsgEntity entity : entities) {
				CRMCreditApiMsgDTO dto = CreditUtil.convertCRMCreditMsgDTOByEntity(entity);
				dtos.add(dto);
			}
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", dtos,pagination);
		}
		
		//手动执行crm接口
		 @RequestMapping(value = "/getDataManual", method = POST, produces = "application/json; charset=utf-8")
			@ResponseBody
			public Map<String, Object> getDataManual() {
			 int num  = crmCreditRecordService.countMsgAll();
				  if(num==0) {
						return GsonUtil.buildMap(1, "当前无接口报错记录", null);	 
				  }
				  mallUserService.scanCrmErrorManual();
				return GsonUtil.buildMap(0, "success", null);
			}
		 //查找crm接口产品信息列表
			@GetMapping(value = "/getCrmCreditProduct")
			public Map<String, Object> getCrmCreditProduct(HttpServletRequest request, 
					@RequestParam int pageNumber,@RequestParam int pageSize) {
				
				int sum = 0 ;		
				List<CRMCreditProductViewEntity> entities = null;
			
					//全量查找
					entities = crmCreditProductService.findByOrderByProductCode(pageNumber, pageSize);
					sum = crmCreditProductService.countAll();
						
		
				List<CRMCreditProductViewDTO> dtos = new ArrayList<CRMCreditProductViewDTO>();
				for (CRMCreditProductViewEntity entity : entities) {
					CRMCreditProductViewDTO dto = CreditUtil.convertCRMCreditProductDTOByEntity(entity);
					dtos.add(dto);
				}
				Pagination pagination = new Pagination(pageNumber, pageSize, sum);
				return GsonUtil.buildMap(0, "ok", dtos,pagination);
			}
}
