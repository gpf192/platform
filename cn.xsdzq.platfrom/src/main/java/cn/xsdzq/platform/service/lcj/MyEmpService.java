package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpService {
	int countAll();
	List<EmpEntity> getAllEmp(int pageNumber, int pageSize);
	
    int countEmpEntityByEmp_nameAndEmp_codeAndSales_department(String emp_name, String emp_code, 
    		long sales_department);
	List<EmpEntity>findByEmp_nameAndEmp_codeAndSales_departmentOrderByEmp_code(String emp_name, String emp_code, 
			long sales_department, int pageNumber, int pageSize);
	
	int countEmpEntityByEmp_name(String emp_name);
	List<EmpEntity>findEmpEntityByEmp_nameOrderByEmp_code(String emp_name, int pageNumber, int pageSize);
	
	int countEmpEntityByEmp_code( String emp_code);
	List<EmpEntity>findEmpEntityByEmp_codeOrderByEmp_code(String emp_code, int pageNumber, int pageSize);
	
    int countEmpEntityBySales_department(long sales_department);
	List<EmpEntity>findEmpEntityBySales_departmentOrderByEmp_code(long sales_department, int pageNumber, int pageSize);
	
	
    int countEmpEntityByEmp_nameAndEmp_code(String emp_name, String emp_code
		);
	List<EmpEntity>findEmpEntityByEmp_nameAndEmp_codeOrderByEmp_code(String emp_name, String emp_code, 
			int pageNumber, int pageSize);
	
    int countEmpEntityByEmp_nameAndSales_department(String emp_name,
    		long sales_department);
	List<EmpEntity>findEmpEntityByEmp_nameAndSales_departmentOrderByEmp_code(String emp_name,  
			long sales_department, int pageNumber, int pageSize);
	
    int countEmpEntityByEmp_codeAndSales_department( String emp_code, 
    		long sales_department);
	List<EmpEntity>findEmpEntityByEmp_codeAndSales_departmentOrderByEmp_code(String emp_code, 
			long sales_department, int pageNumber, int pageSize);
	}


