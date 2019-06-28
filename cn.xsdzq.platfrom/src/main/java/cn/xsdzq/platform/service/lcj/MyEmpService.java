package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpService {
	int countAll();
	List<EmpEntity> getAllEmp(int pageNumber, int pageSize);
	
    /*int countEmpEntityByEmp_nameAndEmp_codeAndSales_department(String emp_name, String emp_code, 
			String sales_department);
	List<EmpEntity>findByEmp_nameAndEmp_codeAndSales_departmentOrderById(String emp_name, String emp_code, 
			String sales_department, int pageNumber, int pageSize);
	
	int countEmpEntityByEmp_nameAndEmp_codeAndSales_department(String emp_name, String emp_code, 
			String sales_department);
	List<EmpEntity>findByEmp_nameAndEmp_codeAndSales_departmentOrderById(String emp_name, String emp_code, 
			String sales_department, int pageNumber, int pageSize);*/
	
	}
