package cn.xsdzq.platform.controller.lcj;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.PrizeRecordDTO;
import cn.xsdzq.platform.service.lcj.PrizeRecordService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;

@Controller
@RequestMapping("/prizeRecord")
public class PrizeRecordController {
	Logger logger = LogManager.getLogger(PrizeRecordController.class.getName());
	@Autowired
	@Qualifier("prizeRecordServiceImpl")
	private PrizeRecordService prizeRecordService;
	
	@RequestMapping(value = "/getPrizeRecord", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getPrizeRecord(HttpServletRequest request,  
			@RequestParam String beginTime, @RequestParam String endTime, @RequestParam String prizeName, @RequestParam String account, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询中奖纪录    +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Date endDate = null;
		Date beginDate = null;
		
		int sum = 0 ;
		List<PrizeRecordEntity> entitys = null;
		int num = MethodUtil.getPrizeRecordMethodNum(beginTime, endTime, prizeName, account);
		if(num == 1) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}
		if(num == 2) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 3) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 4) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}	
		if(num == 5) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}
		if(num == 6) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 7) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 8) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 9) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}
		if(num == 10) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 11) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 12) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}
		if(num == 13) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		
		if(num == 14) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}
		if(num == 15) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}		

					
		List<PrizeRecordDTO> prizeRecordDTOs = new ArrayList<PrizeRecordDTO>();
		for (PrizeRecordEntity entity : entitys) {
			PrizeRecordDTO dto = LcjUtil.convertPrizeRecordDTOByPrizeRecord(entity);
			prizeRecordDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", prizeRecordDTOs, pagination);
	}
}
