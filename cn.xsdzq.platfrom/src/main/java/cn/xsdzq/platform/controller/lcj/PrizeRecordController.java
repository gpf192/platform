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
		List<PrizeResultEntity> entitys = null;
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
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(beginDate, endDate, prizeName, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(beginDate, endDate, prizeName, clientId);
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
			entitys = prizeRecordService.findByPrizeEntity_nameOrderByRecordTime(prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByPrizeEntity_name(prizeName);
		}
		if(num == 6) {
			//查询条件  资金账号
			entitys = prizeRecordService.findByUserEntity_clientIdOrderByRecordTime(clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByUserEntity_clientId(clientId);
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
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndPrizeEntity_nameOrderByRecordTime(beginDate, prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndPrizeEntity_name(beginDate, prizeName);
		}		
		if(num == 9) {
			//查询条件  大于开始时间、资金账号
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTime(beginDate, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndUserEntity_clientId(beginDate, clientId);
		}
		if(num == 10) {
			//查询条件  小于结束时间、奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime(endDate, prizeName, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqualAndPrizeEntity_name(endDate, prizeName);
		}		
		if(num == 11) {
			//查询条件    小于结束时间、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(endDate, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqualAndUserEntity_clientId(endDate, clientId);
		}		
		if(num == 12) {
			//查询条件 奖品名称、资金账号
			entitys = prizeRecordService.findByPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(prizeName, clientId,pageNumber, pageSize);
			sum = prizeRecordService.countByPrizeEntity_nameAndUserEntity_clientId(prizeName, clientId);
		}
		if(num == 13) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime(beginDate,endDate, prizeName,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_name(beginDate,endDate, prizeName);
		}		
		if(num == 14) {
			//查询条件  开始时间、 奖品名称、资金账号
		
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(beginDate, prizeName, clientId,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientId(beginDate, prizeName, clientId);
		}
		if(num == 15) {
			//查询条件 结束时间、 奖品名称、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(endDate, prizeName, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(endDate, prizeName, clientId);
		}		
		if(num == 16) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = prizeRecordService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(beginDate,endDate, clientId,pageNumber, pageSize);
			sum = prizeRecordService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(beginDate,endDate, clientId);
		}	
					
		List<PrizeRecordDTO> prizeRecordDTOs = new ArrayList<PrizeRecordDTO>();
		for (PrizeResultEntity entity : entitys) {
			PrizeRecordDTO dto = LcjUtil.convertPrizeRecordDTOByEntity(entity);
			prizeRecordDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", prizeRecordDTOs, pagination);
	}
}
