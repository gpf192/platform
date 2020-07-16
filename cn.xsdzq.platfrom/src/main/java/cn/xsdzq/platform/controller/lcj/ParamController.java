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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpDTO;
import cn.xsdzq.platform.model.lcj.ParamDTO;
import cn.xsdzq.platform.service.lcj.MyParamService;
import cn.xsdzq.platform.service.lcj.ParamService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.SysUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/param")
public class ParamController {
	Logger logger = LogManager.getLogger(ParamController.class.getName());
	@Autowired
	@Qualifier("myParamServiceImpl")
	private MyParamService myParamService;
	
	@Autowired
	@Qualifier("paramServiceImpl")
	private ParamService paramService;
	
	@RequestMapping(value = "/getParam", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getEmp(HttpServletRequest request,  @RequestParam String code,  
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		
		int sum = 0 ;
		List<ParamEntity> entitys = null;
		if("".equals(code)) {
			entitys = myParamService.getAllParam(pageNumber, pageSize);
			sum = myParamService.countAll();
		}else {
			entitys = myParamService.findByCodeLikeOrderById(code, pageNumber, pageSize);
			sum = myParamService.countByCodeLike(code);
		}	
					
		List<ParamDTO> DTOs = new ArrayList<ParamDTO>();
		for (ParamEntity entity : entitys) {
			ParamDTO dto = SysUtil.convertParamDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}

	@RequestMapping(value = "/addParam", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addParam(HttpServletRequest request, @Validated @RequestBody ParamDTO dto) {
		ParamEntity entity = SysUtil.convertParamEntityByDTO(dto);	
		
		paramService.addInfo(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "addParam" + "; byuser: " + name + ";" + "sysCode:" + entity.getCode() + ";"+ " sysValue:" + entity.getValue() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
	
	@RequestMapping(value = "/deleteParam", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteEmp(HttpServletRequest request, @RequestBody ParamDTO dto) {
		ParamEntity entity = SysUtil.convertParamEntityByDTO(dto);
		
		paramService.deleteInfo(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info(" 删除系统参数 信息 action:" + "deleteParam" + ";" + "user: " + name  +" sysCode: "+entity.getCode()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyParam", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyEmp(HttpServletRequest request, @RequestBody ParamDTO dto) {
		ParamEntity entity = SysUtil.convertParamEntityByDTO(dto);			
		
		paramService.modifyInfo(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "modifyParam" + "; byuser: " + name + ";" + "sysCode:" + entity.getCode() + ";"+ " sysValue:" + entity.getValue() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
}
