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

import cn.xsdzq.platform.entity.LiveRecordEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;
import cn.xsdzq.platform.model.LiveRecordDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.PrizeRecordDTO;
import cn.xsdzq.platform.service.LiveRecordService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;

@Controller
@RequestMapping("/live")
public class LiveController {
	Logger logger = LogManager.getLogger(LiveController.class.getName());
	@Autowired
	@Qualifier("liveRecordServiceImpl")
	private LiveRecordService liveRecordService;
	@RequestMapping(value = "/getLoginRecord", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getLoginRecord(HttpServletRequest request,  
			 @RequestParam String clientId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("查询全量用户登录直播记录  +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		int sum = 0;
		List<LiveRecordEntity> entitys = null;	
		if("".equals(clientId)) {
			//全量查找
			entitys = liveRecordService.getAllLiveRecord(pageNumber, pageSize);
			sum = liveRecordService.countAll();
		}else {
			//根据clientid查询
			entitys = liveRecordService.findByUserEntity_clientIdOrderByRecordTimeDesc(clientId, pageNumber, pageSize);
			sum = liveRecordService.countByUserEntity_clientId(clientId);
		}	
					
		List<LiveRecordDTO> dtos = new ArrayList<LiveRecordDTO>();
		for (LiveRecordEntity entity : entitys) {
			LiveRecordDTO dto = LcjUtil.convertLiveRecordDTOByEntity(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos, pagination);
	}
}
