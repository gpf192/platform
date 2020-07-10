package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Comparator;
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

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.entity.DzhActivityEntity;
import cn.xsdzq.platform.entity.DzhggEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.DzhActivityDTO;
import cn.xsdzq.platform.model.DzhDTO;
import cn.xsdzq.platform.model.KCDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.service.DzhActivityService;
import cn.xsdzq.platform.service.DzhggService;
import cn.xsdzq.platform.service.KCService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.DzhUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.KCUtil;
import cn.xsdzq.platform.util.MethodUtil;

@Controller
@RequestMapping("/dzh")
public class DzhController {
	Logger logger = LogManager.getLogger(DzhController.class.getName());
	@Autowired
	@Qualifier("dzhggServiceImpl")
	private DzhggService dzhggService;
	
	@Autowired
	@Qualifier("dzhActivityServiceImpl")
	private DzhActivityService dzhActivityService;
	
	@RequestMapping(value = "/getDzhgg", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getDzhgg(HttpServletRequest request,  
			@RequestParam String activity, @RequestParam String name, @RequestParam String phone,
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("_______________________________________________________________________");
		if("全部".equals(activity)) {
			activity="";
		}
		int sum = 0 ;
		List<DzhggEntity> entitys = null;
		int num = MethodUtil.getProductSellMethodNum(activity,name,phone);
		if(num == 1) {
			System.out.println("全量查找");
			entitys = dzhggService.getAll(pageNumber, pageSize);
			sum = dzhggService.countAll();
		}
		if(num == 2) {
			System.out.println("三个条件一起查找");
			
			entitys = dzhggService.findByActivityAndNameAndPhoneOrderByRecordtimeDesc(activity, "%"+name+"%", "%"+phone+"%", pageNumber, pageSize);
			sum = dzhggService.countByActivityAndNameAndPhone(activity, "%"+name+"%", "%"+phone+"%");
			
		}
		if(num == 3) {
			//activity, name
			
			entitys = dzhggService.findByActivityAndNameOrderByRecordtimeDesc(activity, "%"+name+"%", pageNumber, pageSize);
			sum = dzhggService.countByActivityAndName(activity, "%"+name+"%");
			
		}
		if(num == 4) {
			////activity, phone
			entitys = dzhggService.findByActivityAndPhoneOrderByRecordtimeDesc(activity, "%"+phone+"%", pageNumber, pageSize);
			sum = dzhggService.countByActivityAndPhone(activity, "%"+phone+"%");
			
			
		}
		if(num == 5) {
			// name,phone
			entitys = dzhggService.findByNameAndPhoneOrderByRecordtimeDesc("%"+name+"%", "%"+phone+"%", pageNumber, pageSize);
			sum = dzhggService.countByNameAndPhone("%"+name+"%", "%"+phone+"%");
			
		}
		if(num == 6) {
			// activity
			entitys = dzhggService.findByActivityOrderByRecordtimeDesc(activity, pageNumber, pageSize);
			sum = dzhggService.countByActivity(activity);
			
		}
		if(num == 7) {
			// name
			entitys = dzhggService.findByNameOrderByRecordtimeDesc("%"+name+"%", pageNumber, pageSize);
			sum = dzhggService.countByName("%"+name+"%");
			
		}
		if(num == 8) {
			// phone
			entitys = dzhggService.findByPhoneOrderByRecordtimeDesc("%"+phone+"%", pageNumber, pageSize);
			sum = dzhggService.countByPhone("%"+phone+"%");
			
		}
			
		List<DzhDTO> DTOs = new ArrayList<DzhDTO>();
		for (DzhggEntity entity : entitys) {
			DzhDTO dto = DzhUtil.convertDzhggDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}
	
	@RequestMapping(value = "/getDzhActivity", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getDzhActivity() {
		List<DzhActivityEntity> list = dzhActivityService.findByOrderByName();
		List<DzhActivityDTO> cDtos = new ArrayList<DzhActivityDTO>();
		for (DzhActivityEntity category : list) {
			DzhActivityDTO dto = DzhUtil.convertDzhActvityDTOByEntity(category);
			cDtos.add(dto);
		}
		//cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}
	
}
