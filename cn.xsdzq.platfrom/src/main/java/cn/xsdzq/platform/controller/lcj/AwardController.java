package cn.xsdzq.platform.controller.lcj;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.lcj.AwardEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultViewEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.AwardDTO;
import cn.xsdzq.platform.model.lcj.AwardResultdDTO;
import cn.xsdzq.platform.model.lcj.BatchPrizeJsonDTO;
import cn.xsdzq.platform.model.lcj.PrizeRecordDTO;
import cn.xsdzq.platform.service.lcj.AwardResultService;
import cn.xsdzq.platform.service.lcj.AwardService;
import cn.xsdzq.platform.service.lcj.PrizeRecordService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/award")
public class AwardController extends BaseController {
	Logger logger = LogManager.getLogger(AwardController.class.getName());
	@Autowired
	@Qualifier("awardServiceImpl")
	private AwardService awardService;
	
	@Autowired
	@Qualifier("awardResultServiceImpl")
	private AwardResultService awardResultService;
	
	@RequestMapping(value = "/getAward", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAward(HttpServletRequest request) {
		List<AwardDTO> infoDTOs = null;
		try {
			List<AwardEntity> infos = awardService.getAllAward();
			infoDTOs = new ArrayList<AwardDTO>();
			for (AwardEntity info : infos) {
				AwardDTO dto = LcjUtil.convertAwardDTOByEntity(info);
				infoDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);
	}
	
	@RequestMapping(value = "/addAward", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addAward(HttpServletRequest request, @RequestBody BatchPrizeJsonDTO jsondto) {
		//解析json,批量添加
		System.out.println("batchPrizeJson: "+jsondto.getBatchPrizeJson());
		List<AwardDTO> dtoList = JSON.parseArray(jsondto.getBatchPrizeJson(),AwardDTO.class);
		for(AwardDTO dto : dtoList) {
		
			AwardEntity entity = LcjUtil.convertEntityByAwardDTO(dto);
			awardService.addAward(entity);
		}
		User user = UserManageUtil.getUser();
		String name = user.getUsername();		
		logger.info("action:" + " award add" + "; user: " + name + ";" + "AwardBatchJson:" + jsondto.getBatchPrizeJson() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
	@RequestMapping(value = "/deleteAward", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAward(HttpServletRequest request, @RequestBody AwardDTO dto) {
		AwardEntity entity = LcjUtil.convertEntityByAwardDTO(dto);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		
		awardService.deleteAward(entity);
		
		logger.info(" 删除理财节奖品信息 action:" + "delete" + ";" + "user: " + name  +" award: "+entity.getAwardName()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyAward", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyAward(HttpServletRequest request, @RequestBody AwardDTO dto) {
		
		AwardEntity entity = LcjUtil.convertEntityByAwardDTO(dto);	
		//判断  总数必须大于等于已经兑换的数量
		if("qjfdj".equals(entity.getImageName())) {
			int resultNumber = awardService.getAwardResultNumber(entity);//目前已经兑换的全家福奖个数
			int total = entity.getImageNumber();//修改后的总数
			
			int residueNumber = total - resultNumber;
			if (residueNumber < 0) {
				String message = dto.getAwardName() + "修改失败！总数必须大于已经兑换的数量";
				
				return GsonUtil.buildMap(-1, message, null);			
				}
		}

	
		awardService.modifyAward(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "award:" + entity.getAwardName() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
	
//兑换组合奖记录查询
	

	
	@RequestMapping(value = "/getAwardResult", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAwardResult(HttpServletRequest request,  
			@RequestParam String beginTime, @RequestParam String endTime, @RequestParam String prizeName, @RequestParam String clientId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询中奖纪录    +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Date endDate = null;
		Date beginDate = null;
		
		int sum = 0 ;
		List<AwardResultViewEntity> entitys = null;
		int num = MethodUtil.getAwardResultMethodNum(beginTime, endTime, prizeName, clientId);
		if(num == 1) {
			//全量查找
			entitys = awardResultService.getAllAwardResult(pageNumber, pageSize);
			sum = awardResultService.countAll();
		}

		if(num == 2) {
			//四个条件一起查询
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(beginDate, endDate, prizeName, clientId, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(beginDate, endDate, prizeName, clientId);
		}		
		if(num == 3) {
			//查询条件  大于开始时间
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualOrderByRecordTime(beginDate, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqual(beginDate);
		}		
		if(num == 4) {
			//查询条件  小于结束时间
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = awardResultService.findByRecordTimeLessThanEqualOrderByRecordTime(endDate, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeLessThanEqual(endDate);
		}	
		if(num == 5) {
			//查询条件  奖品名称
			entitys = awardResultService.findByPrizeCodeOrderByRecordTime(prizeName, pageNumber, pageSize);
			sum = awardResultService.countByPrizeCode(prizeName);
		}
		if(num == 6) {
			//查询条件  资金账号
			entitys = awardResultService.findByClientIdOrderByRecordTime(clientId, pageNumber, pageSize);
			sum = awardResultService.countByClientId(clientId);
		}		
		if(num == 7) {
			//查询条件  大于开始时间、小于结束时间
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(beginDate, endDate,pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
		}		
		if(num == 8) {
			//查询条件  大于开始时间、奖品名称
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(beginDate, prizeName, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndPrizeCode(beginDate, prizeName);
		}		
		if(num == 9) {
			//查询条件  大于开始时间、资金账号
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(beginDate, clientId, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndClientId(beginDate, clientId);
		}
		if(num == 10) {
			//查询条件  小于结束时间、奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = awardResultService.findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(endDate, prizeName, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeLessThanEqualAndPrizeCode(endDate, prizeName);
		}		
		if(num == 11) {
			//查询条件    小于结束时间、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = awardResultService.findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(endDate, clientId, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeLessThanEqualAndClientId(endDate, clientId);
		}		
		if(num == 12) {
			//查询条件 奖品名称、资金账号
			entitys = awardResultService.findByPrizeCodeAndClientIdOrderByRecordTime(prizeName, clientId,pageNumber, pageSize);
			sum = awardResultService.countByPrizeCodeAndClientId(prizeName, clientId);
		}
		if(num == 13) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(beginDate,endDate, prizeName,pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(beginDate,endDate, prizeName);
		}		
		if(num == 14) {
			//查询条件  开始时间、 奖品名称、资金账号
		
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(beginDate, prizeName, clientId,pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(beginDate, prizeName, clientId);
		}
		if(num == 15) {
			//查询条件 结束时间、 奖品名称、资金账号
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(endDate, prizeName, clientId, pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(endDate, prizeName, clientId);
		}		
		if(num == 16) {
			//查询条件 开始时间、 结束时间、 奖品名称
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = awardResultService.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(beginDate,endDate, clientId,pageNumber, pageSize);
			sum = awardResultService.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(beginDate,endDate, clientId);
		}	
					
		List<AwardResultdDTO> dtos = new ArrayList<AwardResultdDTO>();
		for (AwardResultViewEntity entity : entitys) {
			AwardResultdDTO dto = LcjUtil.convertAwardResultDTOByEntity(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos, pagination);
	}
	
}
