package cn.xsdzq.platform.controller.lcj;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.lcj.AwardEntity;
import cn.xsdzq.platform.model.lcj.AwardDTO;
import cn.xsdzq.platform.model.lcj.BatchPrizeJsonDTO;
import cn.xsdzq.platform.service.lcj.AwardService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/award")
public class AwardController extends BaseController {
	Logger logger = LogManager.getLogger(AwardController.class.getName());
	@Autowired
	@Qualifier("prizeServiceImpl")
	private AwardService awardService;
	
	
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
		
	
		awardService.modifyAward(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "award:" + entity.getAwardName() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
}
