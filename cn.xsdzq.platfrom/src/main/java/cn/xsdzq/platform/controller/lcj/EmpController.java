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
import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.lcj.DepartmentEntity;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpDTO;
import cn.xsdzq.platform.service.lcj.DepartmentService;
import cn.xsdzq.platform.service.lcj.EmpService;
import cn.xsdzq.platform.service.lcj.MyEmpService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;
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
	
	@Autowired
	@Qualifier("departmentServiceImpl")
	private DepartmentService departmentService;

	@RequestMapping(value = "/getEmp", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getEmp(HttpServletRequest request,  
			@RequestParam String emp_name, @RequestParam String emp_code,  @RequestParam String departmentCode, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量参赛人员信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		
		int sum = 0 ;
		List<EmpEntity> entitys = null;
		int num = MethodUtil.getEmpMethodNum(emp_name, emp_code, departmentCode);
		
		if(num == 1) {
			//全量查找
			entitys = myEmpService.getAllEmp(pageNumber, pageSize);
			sum = myEmpService.countAll();
		}
		if(num == 2) {
			//查询条件 emp_name, emp_code, sales_department
			entitys = myEmpService.findByEmp_nameAndEmp_codeAndSales_departmentOrderByEmp_code(emp_name, emp_code, departmentCode, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityByEmp_nameAndEmp_codeAndSales_department(emp_name, emp_code, departmentCode);
		}
		if(num == 3) {
			//查询条件  名字
			entitys = myEmpService.findEmpEntityByEmp_nameOrderByEmp_code(emp_name, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityByEmp_name(emp_name);
		}
		if(num == 4) {
			//查询条件  code
			entitys = myEmpService.findEmpEntityByEmp_codeOrderByEmp_code(emp_code, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityByEmp_code(emp_code);
		}
		if(num == 5) {
			//查询条件  部门
			entitys = myEmpService.findEmpEntityBySales_departmentOrderByEmp_code(departmentCode, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityBySales_department(departmentCode);
		}
		if(num == 6) {
			//查询条件  姓名、code
			entitys = myEmpService.findEmpEntityByEmp_nameAndEmp_codeOrderByEmp_code(emp_name, emp_code, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityByEmp_nameAndEmp_code(emp_name, emp_code);
		}
		if(num == 7) {
			//查询条件  姓名、部门
			entitys = myEmpService.findEmpEntityByEmp_nameAndSales_departmentOrderByEmp_code(emp_name, departmentCode, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityByEmp_nameAndSales_department(emp_name, departmentCode);
		}
		if(num == 8) {
			//查询条件  code、部门
			entitys = myEmpService.findEmpEntityByEmp_codeAndSales_departmentOrderByEmp_code(emp_code, departmentCode, pageNumber, pageSize);
			sum = myEmpService.countEmpEntityByEmp_codeAndSales_department(emp_code, departmentCode);
		}
					
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
		DepartmentEntity departmentEntity = departmentService.findDepartmentByCode(entity.getDepartmentCode());
		entity.setDepartmentEntity(departmentEntity);
		
		empService.addEmp(entity);
		//logger.info("action:" + "add" + ";" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
	
	@RequestMapping(value = "/deleteEmp", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteEmp(HttpServletRequest request, @RequestBody EmpDTO dto) {
		EmpEntity entity = LcjUtil.convertEntityByEmpDTO(dto);
		
		DepartmentEntity departmentEntity = departmentService.findDepartmentByCode(entity.getDepartmentCode());
		entity.setDepartmentEntity(departmentEntity);
		
		empService.deleteEmp(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info(" 删除理财节 参赛用户  emp 信息 action:" + "delete" + ";" + "user: " + name  +" empname: "+entity.getEmpName()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyEmp", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyEmp(HttpServletRequest request, @RequestBody EmpDTO dto) {
		EmpEntity entity = LcjUtil.convertEntityByEmpDTO(dto);			
		DepartmentEntity departmentEntity = departmentService.findDepartmentByCode(entity.getDepartmentCode());
		entity.setDepartmentEntity(departmentEntity);
		empService.modifyEmp(entity);
		//logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
}
