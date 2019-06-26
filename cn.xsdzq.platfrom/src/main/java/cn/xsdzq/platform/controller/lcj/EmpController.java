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

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpDTO;
import cn.xsdzq.platform.model.lcj.ProductDTO;
import cn.xsdzq.platform.service.lcj.EmpService;
import cn.xsdzq.platform.service.lcj.MyEmpService;
import cn.xsdzq.platform.service.lcj.MyPrizeService;
import cn.xsdzq.platform.service.lcj.PrizeService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/emp")
public class EmpController extends BaseController {
	Logger logger = LogManager.getLogger(EmpController.class.getName());
	@Autowired
	@Qualifier("empServiceImpl")
	private EmpService empService;
	
	@Autowired
	@Qualifier("myEmpServiceImpl")
	private MyEmpService myEmpService;

	@RequestMapping(value = "/getEmp", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getEmp(HttpServletRequest request,  
//			@RequestParam String beginTime, @RequestParam String endTime, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量参赛人员信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		/*Date endDate = null;
		Date beginDate = null;*/
		
		int sum = 0 ;
		List<EmpEntity> entitys = null;
		//int num = MethodUtil.getMethodNum(beginTime,endTime);
		
			entitys = myEmpService.getAllEmp(pageNumber, pageSize);
			sum = myEmpService.countAll();
		
	
					
		List<EmpDTO> empDTOs = new ArrayList<EmpDTO>();
		for (EmpEntity entity : entitys) {
			EmpDTO dto = LcjUtil.convertEmpDTOByEntity(entity);
			empDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", empDTOs, pagination);
	}

	@RequestMapping(value = "/addEmp", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addEmp(HttpServletRequest request, @Validated @RequestBody EmpDTO dto) {
		EmpEntity entity = LcjUtil.convertEntityByEmpDTO(dto);	
		// 插入创建人
		
		empService.addEmp(entity);
		//logger.info("action:" + "add" + ";" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteEmp", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteEmp(HttpServletRequest request, @RequestBody EmpDTO dto) {
		EmpEntity entity = LcjUtil.convertEntityByEmpDTO(dto);	
		
		empService.deleteEmp(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info(" 删除理财节 参赛用户  emp 信息 action:" + "delete" + ";" + "user: " + name  +" empname: "+entity.getEmp_name()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyEmp", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyEmp(HttpServletRequest request, @RequestBody EmpDTO dto) {
		EmpEntity entity = LcjUtil.convertEntityByEmpDTO(dto);			
		
		empService.modifyEmp(entity);
		//logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
}
