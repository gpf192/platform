package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpRepository extends PagingAndSortingRepository<EmpEntity, Long> {
	Page<EmpEntity> findByEnableOrderById(int enable, Pageable pageable);
	
	int countByEnable(int enable);
	
	
	Page<EmpEntity> findByEnableAndEmpNameLikeAndEmpCodeAndDepartmentCodeOrderByEmpCode(int enable, String emp_name, String emp_code, 
			String sales_department, Pageable pageable);
	int countByEnableAndEmpNameLikeAndEmpCodeAndDepartmentCode(int enable, String emp_name, String emp_code, 
			String sales_department);
	
	Page<EmpEntity> findByEnableAndEmpNameLikeOrderByEmpCode(int enable, String emp_name, Pageable pageable);
	int countByEnableAndEmpNameLike(int enable,String emp_name);
	
	Page<EmpEntity> findByEnableAndEmpCodeOrderByEmpCode(int enable,String c, Pageable pageable);
	int countByEnableAndEmpCode(int enable,String emp_code);
	
	Page<EmpEntity> findByEnableAndDepartmentCodeOrderByEmpCode(int enable,String sales_department, Pageable pageable);
	int countByEnableAndDepartmentCode(int enable,String sales_department);
	
	Page<EmpEntity> findByEnableAndEmpNameLikeAndEmpCodeOrderByEmpCode(int enable,String emp_name, String emp_code, 
			 Pageable pageable);
	int countByEnableAndEmpNameLikeAndEmpCode(int enable,String emp_name, String emp_code);
	
	Page<EmpEntity> findByEnableAndEmpNameLikeAndDepartmentCodeOrderByEmpCode(int enable,String emp_name, 
			String sales_department, Pageable pageable);
	int countByEnableAndEmpNameLikeAndDepartmentCode(int enable,String emp_name, 
			String sales_department);
	
	Page<EmpEntity> findByEnableAndEmpCodeAndDepartmentCodeOrderByEmpCode(int enable,String emp_code, 
			String sales_department, Pageable pageable);
	int countByEnableAndEmpCodeAndDepartmentCode(int enable,String emp_code, 
			String sales_department);
	
	
}
