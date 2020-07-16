package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpRepository extends PagingAndSortingRepository<EmpEntity, Long> {
	Page<EmpEntity> findByEnableOrderById(int enable, Pageable pageable);
	
	int countByEnable(int enable);
	
	
	Page<EmpEntity> findByEnableAndEmpNameAndEmpCodeAndDepartmentCodeOrderByEmpCode(int enable, String emp_name, String emp_code, 
			String sales_department, Pageable pageable);
	int countByEnableAndEmpNameAndEmpCodeAndDepartmentCode(int enable, String emp_name, String emp_code, 
			String sales_department);
	
	Page<EmpEntity> findByEnableAndEmpNameOrderByEmpCode(int enable, String emp_name, Pageable pageable);
	int countByEnableAndEmpName(int enable,String emp_name);
	
	Page<EmpEntity> findByEnableAndEmpCodeOrderByEmpCode(int enable,String c, Pageable pageable);
	int countByEnableAndEmpCode(int enable,String emp_code);
	
	Page<EmpEntity> findByEnableAndDepartmentCodeOrderByEmpCode(int enable,String sales_department, Pageable pageable);
	int countByEnableAndDepartmentCode(int enable,String sales_department);
	
	Page<EmpEntity> findByEnableAndEmpNameAndEmpCodeOrderByEmpCode(int enable,String emp_name, String emp_code, 
			 Pageable pageable);
	int countByEnableAndEmpNameAndEmpCode(int enable,String emp_name, String emp_code);
	
	Page<EmpEntity> findByEnableAndEmpNameAndDepartmentCodeOrderByEmpCode(int enable,String emp_name, 
			String sales_department, Pageable pageable);
	int countByEnableAndEmpNameAndDepartmentCode(int enable,String emp_name, 
			String sales_department);
	
	Page<EmpEntity> findByEnableAndEmpCodeAndDepartmentCodeOrderByEmpCode(int enable,String emp_code, 
			String sales_department, Pageable pageable);
	int countByEnableAndEmpCodeAndDepartmentCode(int enable,String emp_code, 
			String sales_department);
	
	
}
