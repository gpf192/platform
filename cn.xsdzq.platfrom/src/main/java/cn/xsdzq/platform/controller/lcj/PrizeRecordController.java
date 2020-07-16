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

import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;
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
			@RequestParam String beginTime, @RequestParam String endTime, @RequestParam String prizeName, @RequestParam String clientId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询中奖纪录    +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Date endDate = null;
		Date beginDate = null;
		
		int sum = 0 ;
		List<PrizeResultViewEntity> entitys = null;
		int num = MethodUtil.getPrizeRecordMethodNum(beginTime, endTime, prizeName, clientId);
		if(num == 1) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecord(pageNumber, pageSize);
			sum = prizeRecordService.countAll();
		}

		if(num == 2) {
			//四个条件一起查询
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(beginDate, endDate, prizeName, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(beginDate, endDate, prizeName, clientId);
		}		
		if(num == 3) {
			//查询条件  大于开始时间
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualOrderByRecordTime(beginDate, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqual(beginDate);
		}		
		if(num == 4) {
			//查询条件  小于结束时间
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualOrderByRecordTime(endDate, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqual(endDate);
		}	
		if(num == 5) {
			//查询条件  奖品名称
			entitys = prizeRecordService.findByPrizeCodeOrderByRecordTime(prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByPrizeCode(prizeName);
		}
		if(num == 6) {
			//查询条件  资金账号
			entitys = prizeRecordService.findByClientIdOrderByRecordTime(clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByClientId(clientId);
		}		
		if(num == 7) {
			//查询条件  大于开始时间、小于结束时间
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(beginDate, endDate,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
		}		
		if(num == 8) {
			//查询条件  大于开始时间、奖品名称
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(beginDate, prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndPrizeCode(beginDate, prizeName);
		}		
		if(num == 9) {
			//查询条件  大于开始时间、资金账号
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(beginDate, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndClientId(beginDate, clientId);
		}
		if(num == 10) {
			//查询条件  小于结束时间、奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(endDate, prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqualAndPrizeCode(endDate, prizeName);
		}		
		if(num == 11) {
			//查询条件    小于结束时间、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(endDate, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqualAndClientId(endDate, clientId);
		}		
		if(num == 12) {
			//查询条件 奖品名称、资金账号
			entitys = prizeRecordService.findByPrizeCodeAndClientIdOrderByRecordTime(prizeName, clientId,pageNumber, pageSize);
			sum = prizeRecordService.countByPrizeCodeAndClientId(prizeName, clientId);
		}
		if(num == 13) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(beginDate,endDate, prizeName,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(beginDate,endDate, prizeName);
		}		
		if(num == 14) {
			//查询条件  开始时间、 奖品名称、资金账号
		
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(beginDate, prizeName, clientId,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(beginDate, prizeName, clientId);
		}
		if(num == 15) {
			//查询条件 结束时间、 奖品名称、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(endDate, prizeName, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(endDate, prizeName, clientId);
		}		
		if(num == 16) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(beginDate,endDate, clientId,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(beginDate,endDate, clientId);
		}	
					
		List<PrizeRecordDTO> prizeRecordDTOs = new ArrayList<PrizeRecordDTO>();
		for (PrizeResultViewEntity entity : entitys) {
			PrizeRecordDTO dto = LcjUtil.convertPrizeRecordDTOByEntity(entity);
			prizeRecordDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", prizeRecordDTOs, pagination);
	}
}
