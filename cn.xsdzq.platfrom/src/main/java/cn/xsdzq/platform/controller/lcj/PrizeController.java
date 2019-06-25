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

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.model.KCDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.PrizeDTO;
import cn.xsdzq.platform.service.KCService;
import cn.xsdzq.platform.service.lcj.PrizeService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.KCUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;

@Controller
@RequestMapping("/prize")
public class PrizeController extends BaseController {
	Logger logger = LogManager.getLogger(PrizeController.class.getName());
	@Autowired
	@Qualifier("prizeServiceImpl")
	private PrizeService prizeService;
	
	@RequestMapping(value = "/getPrize", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getPrize(HttpServletRequest request,  
//			@RequestParam String beginTime, @RequestParam String endTime, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询奖品信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Date endDate = null;
		Date beginDate = null;
		
		int sum = 0 ;
		List<PrizeEntity> entitys = null;
		//int num = MethodUtil.getMethodNum(beginTime,endTime);
		
			entitys = prizeService.getAllPrize(pageNumber, pageSize);
			sum = prizeService.countAll();
		
	
					
		List<PrizeDTO> prizeDTOs = new ArrayList<PrizeDTO>();
		for (PrizeEntity entity : entitys) {
			PrizeDTO dto = LcjUtil.convertPrizeDTOByPrize(entity);
			prizeDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", prizeDTOs, pagination);
	}
}
