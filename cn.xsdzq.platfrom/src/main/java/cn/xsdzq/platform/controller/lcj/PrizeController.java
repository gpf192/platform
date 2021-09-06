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
import cn.xsdzq.platform.entity.lcj.LcjPrizeEntity;
import cn.xsdzq.platform.entity.lcj.LcjPrizeNumberEntity;
import cn.xsdzq.platform.entity.lcj.LcjPrizeRecordEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.BatchPrizeJsonDTO;
import cn.xsdzq.platform.model.lcj.KmhPrizeRecordDTO;
import cn.xsdzq.platform.model.lcj.PrizeDTO;
import cn.xsdzq.platform.model.lcj.PrizeNumDTO;
import cn.xsdzq.platform.service.lcj.LciPrizeRecordService;
import cn.xsdzq.platform.service.lcj.LcjPrizeService;
import cn.xsdzq.platform.service.lcj.MyPrizeService;
import cn.xsdzq.platform.service.lcj.PrizeService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/prize")
public class PrizeController extends BaseController {
	Logger logger = LogManager.getLogger(PrizeController.class.getName());
	@Autowired
	@Qualifier("prizeServiceImpl")
	private PrizeService prizeService;
	
	@Autowired
	@Qualifier("myPrizeServiceImpl")
	private MyPrizeService myPrizeService;
	
	@Autowired
	@Qualifier("lcjPrizeServiceImpl")
	private LcjPrizeService lcjPrizeService;
	
	@Autowired
	@Qualifier("lcjPrizeRecordServiceImpl")
	private LciPrizeRecordService prizeRecordService;
	
	/*@RequestMapping(value = "/getPrize", method = GET, produces = "application/json; charset=utf-8")
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
	}*/
	
	@RequestMapping(value = "/getPrize", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getPrize(HttpServletRequest request) {
		List<PrizeDTO> infoDTOs = null;
		try {
			List<PrizeEntity> infos = prizeService.getAllPrize();
			infoDTOs = new ArrayList<PrizeDTO>();
			for (PrizeEntity info : infos) {
				PrizeDTO dto = LcjUtil.convertPrizeDTOByPrize(info);
				infoDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);
	}
	
	
	@RequestMapping(value = "/addPrize", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addPrize(HttpServletRequest request, @RequestBody BatchPrizeJsonDTO jsondto) {
		//解析json,批量添加
		System.out.println("batchPrizeJson: "+jsondto.getBatchPrizeJson());
		List<PrizeDTO> dtoList = JSON.parseArray(jsondto.getBatchPrizeJson(),PrizeDTO.class);
		for(PrizeDTO dto : dtoList) {
			PrizeEntity entity = LcjUtil.convertEntityByPrizeDTO(dto);
			Date date = new Date();
			entity.setCreatetime(date);			
			prizeService.addPrize(entity);
		}
		User user = UserManageUtil.getUser();
		String name = user.getUsername();		
		logger.info("action:" + " prize add" + "; user: " + name + ";" + "prizeBatchJson:" + jsondto.getBatchPrizeJson() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deletePrize", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePrize(HttpServletRequest request, @RequestBody PrizeDTO dto) {
		PrizeEntity entity = LcjUtil.convertEntityByPrizeDTO(dto);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		
		prizeService.deletePrize(entity);
		
		logger.info(" 删除理财节奖品信息 action:" + "delete" + ";" + "user: " + name  +" prize: "+entity.getName()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyPrize", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyPrize(HttpServletRequest request, @RequestBody PrizeDTO dto) {
		PrizeEntity entity = LcjUtil.convertEntityByPrizeDTO(dto);	
		
	
		Date date = new Date();
		entity.setModifytime(date);
		prizeService.modifyPrize(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "prize:" + entity.getName() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
	
	/**
	 * //818接口
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/getLcjPrize", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getLcjPrize(HttpServletRequest request) {
		List<PrizeDTO> infoDTOs = null;
		try {
			List<LcjPrizeEntity> infos = lcjPrizeService.getAllPrize();
			infoDTOs = new ArrayList<PrizeDTO>();
			for (LcjPrizeEntity info : infos) {
				PrizeDTO dto = LcjUtil.convertPrizeDTOByLcjPrize(info);
				infoDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);
	}
	

	@RequestMapping(value = "/addLcjPrize", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addLcjPrize(HttpServletRequest request, @RequestBody BatchPrizeJsonDTO jsondto) {
		//解析json,批量添加
		System.out.println("batchPrizeJson: "+jsondto.getBatchPrizeJson());
		List<PrizeDTO> dtoList = JSON.parseArray(jsondto.getBatchPrizeJson(),PrizeDTO.class);
		for(PrizeDTO dto : dtoList) {
			LcjPrizeEntity entity = LcjUtil.convertLcjEntityByPrizeDTO(dto);
			Date date = new Date();
			entity.setCreatetime(date);			
			lcjPrizeService.addPrize(entity);
		}
		User user = UserManageUtil.getUser();
		String name = user.getUsername();		
		logger.info("action:" + " prize add" + "; user: " + name + ";" + "prizeBatchJson:" + jsondto.getBatchPrizeJson() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteLcjPrize", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteLcjPrize(HttpServletRequest request, @RequestBody PrizeDTO dto) {
		LcjPrizeEntity entity = LcjUtil.convertLcjEntityByPrizeDTO(dto);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		
		lcjPrizeService.deletePrize(entity);
		
		logger.info(" 删除理财节奖品信息 action:" + "delete" + ";" + "user: " + name  +" prize: "+entity.getName()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyLcjPrize", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyLcjPrize(HttpServletRequest request, @RequestBody PrizeDTO dto) {
		LcjPrizeEntity entity = LcjUtil.convertLcjEntityByPrizeDTO(dto);	
		
	
		Date date = new Date();
		entity.setModifytime(date);
		lcjPrizeService.modifyPrize(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "prize:" + entity.getName() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
	
  // 818 
	@RequestMapping(value = "/userGetChanceList818", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> userGetChanceList(HttpServletRequest request,  
			 @RequestParam String clientName, @RequestParam String clientId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<LcjPrizeRecordEntity> entitys = null;
		int num = MethodUtil.getMethodNum(clientName, clientId);
		if(num == 1) {
			//全量查找
			entitys = prizeRecordService.getAllPrizeRecordForKmh(pageNumber, pageSize);
			sum = prizeRecordService.countPrizeRecodAll();
		}	
		if(num == 2) {
			//全量查找
			entitys = prizeRecordService.findRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(clientName, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLike(clientName, clientId);
		}
		if(num == 3) {
			//全量查找
			entitys = prizeRecordService.findRecordByUserEntity_clientNameLikeOrderByNumberDesc(clientName, pageNumber, pageSize);
			sum = prizeRecordService.countRecordByUserEntity_clientNameLike(clientName);
		}
		if(num == 4) {
			//全量查找
			entitys = prizeRecordService.findRecordByUserEntity_clientIdLikeOrderByNumberDesc(clientId, pageNumber, pageSize);
			sum = prizeRecordService.countRecordByUserEntity_clientIdLike(clientId);
		}
		List<KmhPrizeRecordDTO> dtos = new ArrayList<KmhPrizeRecordDTO>();
		for (LcjPrizeRecordEntity entity : entitys) {
			KmhPrizeRecordDTO dto = LcjUtil.convertLcjPrizeRecordDTOByEntity(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos, pagination);
	}
	
	@RequestMapping(value = "/userTotalChanceList818", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> userTotalChanceList(HttpServletRequest request,  
			 @RequestParam String clientName, @RequestParam String clientId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<LcjPrizeNumberEntity> entitys = null;
		int num = MethodUtil.getMethodNum(clientName, clientId);
		if(num == 1) {
			//全量查找
			entitys = prizeRecordService.getPrizeNumberForKmh(pageNumber, pageSize);
			sum = prizeRecordService.countPrizeNumberForKmh();
		}
		if(num == 2) {
			//clientName ,clientId
			entitys = prizeRecordService.findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(clientName, clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(clientName, clientId);
		}
		if(num == 3) {
			//clientName
			entitys = prizeRecordService.findByUserEntity_clientNameLikeOrderByNumberDesc(clientName, pageNumber, pageSize);
			sum = prizeRecordService.countByUserEntity_clientNameLike(clientName);
		}
		if(num == 4) {
			//clientId
			entitys = prizeRecordService.findByUserEntity_clientIdLikeOrderByNumberDesc(clientId, pageNumber, pageSize);
			sum = prizeRecordService.countByUserEntity_clientIdLike(clientId);
		}
		List<PrizeNumDTO> dtos = new ArrayList<PrizeNumDTO>();
		for (LcjPrizeNumberEntity entity : entitys) {
			PrizeNumDTO dto = LcjUtil.convertLcjPrizeNumDTOByEntity(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos, pagination);
	}
}
