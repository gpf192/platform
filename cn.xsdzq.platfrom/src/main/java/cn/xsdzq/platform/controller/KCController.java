package cn.xsdzq.platform.controller;

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

import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.model.KCDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.service.KCService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.KCUtil;
import cn.xsdzq.platform.util.MethodUtil;

@Controller
@RequestMapping("/kechuang")
public class KCController extends BaseController {
	Logger logger = LogManager.getLogger(KCController.class.getName());
	@Autowired
	@Qualifier("kcServiceImpl")
	private KCService kcService;
	
	@RequestMapping(value = "/getKCInfos", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getKCInfos(HttpServletRequest request,  
			@RequestParam String beginTime, @RequestParam String endTime, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("jintu   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Date endDate = null;
		Date beginDate = null;
		/*if(!"".equals(beginTime)) {
			 endDate = DateUtil.stringToDate(endTime);
			 beginDate = DateUtil.stringToDate(beginTime);
		}*/
		int sum = 0 ;
		List<CustomerMobileEntity> entitys = null;
		int num = MethodUtil.getMethodNum(beginTime,endTime);
		if(num == 1) {
			System.out.println("全量查找");
			entitys = kcService.getKCInfos(pageNumber, pageSize);
			sum = kcService.countAll();
		}
		if(num == 2) {
			System.out.println("按时区间查询");
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			 beginDate = DateUtil.stringToDate(beginTime);
			entitys = kcService.findByEndTimeLessThanEqualAndBeginTimeGreaterThanEqualOrderByCreatetimeDesc(endDate, beginDate,  pageNumber, pageSize);
			sum = kcService.countByEndTimeLessThanEqualAndBeginTimeGreaterThanEqual(endDate, beginDate);
			
		}
		if(num == 3) {
			System.out.println("按时间查询,只大于开始时间");
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = kcService.findByBeginTimeGreaterThanEqualOrderByCreatetimeDesc(beginDate,  pageNumber, pageSize);
			sum = kcService.countByBeginTimeGreaterThanEqual(beginDate);
			
		}
		if(num == 4) {
			System.out.println("按时间查询,只小于结束时间" + endTime +" 23:59:59");			
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = kcService.findByEndTimeLessThanEqualOrderByCreatetimeDesc(endDate, pageNumber, pageSize);
			sum = kcService.countByEndTimeLessThanEqual(endDate);
			
		}

			
		List<KCDTO> KCDTOs = new ArrayList<KCDTO>();
		for (CustomerMobileEntity entity : entitys) {
			KCDTO dto = KCUtil.convertInfoDTOByInfo(entity);
			KCDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", KCDTOs, pagination);
	}
}
