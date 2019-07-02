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
import cn.xsdzq.platform.util.DateUtil;
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
			//四个条件一起查询
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(beginDate, endDate, prizeName, account, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccount(beginDate, endDate, prizeName, account);
		}		
		if(num == 3) {
			//查询条件  大于开始时间
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualOrderByCreatetime(beginDate, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqual(beginDate);
		}		
		if(num == 4) {
			//查询条件  小于结束时间
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByCreatetimeLessThanEqualOrderByCreatetime(endDate, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeLessThanEqual(endDate);
		}	
		if(num == 5) {
			//查询条件  奖品名称
			entitys = prizeRecordService.findByPrizeNameOrderByCreatetime(prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByPrizeName(prizeName);
		}
		if(num == 6) {
			//查询条件  资金账号
			entitys = prizeRecordService.findByAccountOrderByCreatetime(account, pageNumber, pageSize);
			sum = prizeRecordService.countByAccount(account);
		}		
		if(num == 7) {
			//查询条件  大于开始时间、小于结束时间
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualOrderByCreatetime(beginDate, endDate,pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqual(beginDate, endDate);
		}		
		if(num == 8) {
			//查询条件  大于开始时间、奖品名称
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualAndPrizeNameOrderByCreatetime(beginDate, prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqualAndPrizeName(beginDate, prizeName);
		}		
		if(num == 9) {
			//查询条件  大于开始时间、资金账号
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualAndAccountOrderByCreatetime(beginDate, account, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqualAndAccount(beginDate, account);
		}
		if(num == 10) {
			//查询条件  小于结束时间、奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(endDate, prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeLessThanEqualAndPrizeName(endDate, prizeName);
		}		
		if(num == 11) {
			//查询条件    小于结束时间、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByCreatetimeLessThanEqualAndAccountOrderByCreatetime(endDate, account, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeLessThanEqualAndAccount(endDate, account);
		}		
		if(num == 12) {
			//查询条件 奖品名称、资金账号
			entitys = prizeRecordService.findByPrizeNameAndAccountOrderByCreatetime(prizeName, account,pageNumber, pageSize);
			sum = prizeRecordService.countByPrizeNameAndAccount(prizeName, account);
		}
		if(num == 13) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(beginDate,endDate, prizeName,pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeName(beginDate,endDate, prizeName);
		}		
		if(num == 14) {
			//查询条件  开始时间、 奖品名称、资金账号
		
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeGreaterThanEqualAndPrizeNameAndAccountOrderByCreatetime(beginDate, prizeName, account,pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeGreaterThanEqualAndPrizeNameAndAccount(beginDate, prizeName, account);
		}
		if(num == 15) {
			//查询条件 结束时间、 奖品名称、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(endDate, prizeName, account, pageNumber, pageSize);
			sum = prizeRecordService.countByCreatetimeLessThanEqualAndPrizeNameAndAccount(endDate, prizeName, account);
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
