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
			@RequestParam String username, @RequestParam String orderId,
			@RequestParam String tgName, @RequestParam String productName,
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户订单 信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");		
		int sum = 0 ;
		
		int num = MethodUtil.getUserOrderForMethodNum(username, orderId, tgName, productName);
		List<UserOrderDTO> userOrderDTOs = null;
		List<ThxOrderEntity> entitys = null;
		if(num == 1) {
			//全量查找
			entitys = userOrderService.getAllProduct(pageNumber, pageSize);
			sum = userOrderService.countAll();	
		}
		if(num == 2) {
			//4个条件一起查询
			System.out.println("into   2 ____");
			entitys = userOrderService.findByUsernameAndOrderIdAndTgNameAndProductNameOrderByOrderId(username, orderId, tgName, productName, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndOrderIdAndTgNameAndProductName(username, orderId, tgName, productName);
		}
		if(num == 3) {
			//查询条件：username\orderId\tgName\
			entitys = userOrderService.findByUsernameAndOrderIdAndTgNameOrderByOrderId(username, orderId, tgName, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndOrderIdAndTgName(username, orderId, tgName);
		}
		if(num == 4) {
			//查询条件：username\orderId\\productName
			entitys = userOrderService.findByUsernameAndOrderIdAndProductNameOrderByOrderId(username, orderId, productName, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndOrderIdAndProductName(username, orderId, productName);
		}
		if(num == 5) {
			//查询条件：username\\tgName\productName
			entitys = userOrderService.findByUsernameAndTgNameAndProductNameOrderByOrderId(username, tgName, productName, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndTgNameAndProductName(username, tgName, productName);
		}
		if(num == 6) {
			//查询条件：\orderId\tgName\productName
			entitys = userOrderService.findByOrderIdAndTgNameAndProductNameOrderByOrderId(orderId, tgName, productName, pageNumber, pageSize);
			sum = userOrderService.countByOrderIdAndTgNameAndProductName(orderId, tgName, productName);
		}
		if(num == 7) {
			////查询条件：username\orderId\\
			entitys = userOrderService.findByUsernameAndOrderIdOrderByOrderId(username, orderId, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndOrderId(username, orderId);
		}
		if(num == 8) {
			//查询条件：username\\tgName\
			entitys = userOrderService.findByUsernameAndTgNameOrderByOrderId(username, tgName, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndTgName(username, tgName);
		}
		if(num == 9) {
			//username\\\productName
			entitys = userOrderService.findByUsernameAndProductNameOrderByOrderId(username, productName, pageNumber, pageSize);
			sum = userOrderService.countByUsernameAndProductName(username, productName);
		}
		if(num == 10) {
			//查询条件：\orderId\tgName\
			entitys = userOrderService.findByOrderIdAndTgNameOrderByOrderId(orderId, tgName, pageNumber, pageSize);
			sum = userOrderService.countByOrderIdAndTgName(orderId, tgName);
		}
		if(num == 11) {
			//查询条件：\orderId\\productName
			entitys = userOrderService.findByOrderIdAndProductNameOrderByOrderId(orderId, productName, pageNumber, pageSize);
			sum = userOrderService.countByOrderIdAndProductName(orderId, productName);
		}
		if(num == 12) {
			//查询条件：\\tgName\productName
			entitys = userOrderService.findByTgNameAndProductNameOrderByOrderId(tgName, productName, pageNumber, pageSize);
			sum = userOrderService.countByTgNameAndProductName(tgName, productName);
		}
		if(num == 13) {
			//查询条件：username 
			entitys = userOrderService.findByUsernameOrderByOrderId(username, pageNumber, pageSize);
			sum = userOrderService.countByUsername(username);
		}
		if(num == 14) {
			////查询条件： \orderId
			entitys = userOrderService.findByOrderIdOrderByOrderId(orderId, pageNumber, pageSize);
			sum = userOrderService.countByOrderId(orderId);
		}
		if(num == 15) {
			//查询条件：tgName
			entitys = userOrderService.findByTgNameOrderByOrderId(tgName, pageNumber, pageSize);
			sum = userOrderService.countByTgName(tgName);
		}
		if(num == 16) {
			//查询条件：productName
			entitys = userOrderService.findByProductNameOrderByOrderId(productName, pageNumber, pageSize);
			sum = userOrderService.countByProductName(productName);
		}
						
			userOrderDTOs = new ArrayList<UserOrderDTO>();
			for (ThxOrderEntity entity : entitys) {
				UserOrderDTO dto = ThxUtil.convertUserOrderDTOByEntity(entity);
				userOrderDTOs.add(dto);
			}
		
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", userOrderDTOs, pagination);
	}
}
