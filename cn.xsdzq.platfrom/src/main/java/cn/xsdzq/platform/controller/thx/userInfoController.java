package cn.xsdzq.platform.controller.thx;

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

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;
import cn.xsdzq.platform.entity.thx.UserRiskEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.thx.UserOrderDTO;
import cn.xsdzq.platform.model.thx.UserRiskDTO;
import cn.xsdzq.platform.service.thx.UserOrderService;
import cn.xsdzq.platform.service.thx.UserRiskService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.ThxUtil;

@Controller
@RequestMapping("/thx")
public class userInfoController extends BaseController{
	Logger logger = LogManager.getLogger(userInfoController.class.getName());

	@Autowired
	@Qualifier("userRiskServiceImpl")
	private UserRiskService userRiskService;
	
	@Autowired
	@Qualifier("userOrderServiceImpl")
	private UserOrderService userOrderService;
	
	@RequestMapping(value = "/getRiskInfo", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getRiskInfo(HttpServletRequest request, 
			@RequestParam String beginTime, @RequestParam String endTime, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户风险信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");		
		Date endDate = null;
		Date beginDate = null;
		int sum = 0 ;
		int num = MethodUtil.getMethodNum(beginTime,endTime);
		List<UserRiskEntity> entitys = null;
		if(num == 1) {
			System.out.println("全量查找");
			entitys = userRiskService.getAllUserRisk(pageNumber, pageSize);
			sum = userRiskService.countAll();
		}

		if(num == 2) {
			System.out.println("按时区间查询");
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			 beginDate = DateUtil.stringToDate(beginTime);
			entitys = userRiskService.findByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(endDate, beginDate,  pageNumber, pageSize);
			sum = userRiskService.countByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqual(endDate, beginDate);
			
		}
		if(num == 3) {
			System.out.println("按时间查询,只大于开始时间");
			beginDate = DateUtil.stringToDate(beginTime);
			entitys = userRiskService.findByEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(beginDate,  pageNumber, pageSize);
			sum = userRiskService.countByEvaluationTimeGreaterThanEqual(beginDate);
			
		}
		if(num == 4) {
			System.out.println("按时间查询,只小于结束时间" + endTime +" 23:59:59");			
			endDate = DateUtil.stringToDateAndSeconds(endTime);
			entitys = userRiskService.findByEvaluationTimeLessThanEqualOrderByEvaluationTimeDesc(endDate, pageNumber, pageSize);
			sum = userRiskService.countByEvaluationTimeLessThanEqual(endDate);
			
		}							
			List<UserRiskDTO> UserRiskDTOs  = new ArrayList<UserRiskDTO>();
			for (UserRiskEntity entity : entitys) {
				UserRiskDTO dto = ThxUtil.convertDTOByEntity(entity);
				UserRiskDTOs.add(dto);
			}
		
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", UserRiskDTOs, pagination);
	}

	@RequestMapping(value = "/getUserOrder", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getUserOrder(HttpServletRequest request,  
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户订单 信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");		
		int sum = 0 ;
		List<UserOrderDTO> userOrderDTOs = null;
		try {
			List<ThxOrderEntity> entitys = null;		
				entitys = userOrderService.getAllProduct(pageNumber, pageSize);
				sum = userOrderService.countAll();
						
				userOrderDTOs = new ArrayList<UserOrderDTO>();
			for (ThxOrderEntity entity : entitys) {
				UserOrderDTO dto = ThxUtil.convertUserOrderDTOByEntity(entity);
				userOrderDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", userOrderDTOs, pagination);
	}
}
