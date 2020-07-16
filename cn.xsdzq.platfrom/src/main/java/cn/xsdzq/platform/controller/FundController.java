package cn.xsdzq.platform.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;
import cn.xsdzq.platform.model.Hello;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.service.thx.ThxOrderService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.ThxUtil;

@Controller
@RequestMapping("/fund")
public class FundController {
	

	Logger logger = LogManager.getLogger(FundController.class.getName());
	
	@Autowired
	@Qualifier("thxOrderServiceImpl")
	private ThxOrderService thxOrderService;
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> receiveFund(@RequestParam Map<String, String> params){
		
		String body = params.toString();
		logger.info("post:---"+body);
		
		ThxOrderEntity order = ThxUtil.changeMapToEntity(params);
		thxOrderService.addInfo(order);
		Hello hello =new Hello();
		hello.setName(body);
		return GsonUtil.buildMap(0, "ok", hello);	
		
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> receiveGetFund(@RequestParam Map<String, String> params){
		String body = params.toString();
		logger.info("get:---"+body);
		
		ThxOrderEntity order = ThxUtil.changeMapToEntity(params);
		thxOrderService.addInfo(order);
		
		Hello hello =new Hello();
		hello.setName(body);
		return GsonUtil.buildMap(0, "ok", hello);	
		
	}
	

}
