package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpRepository extends PagingAndSortingRepository<EmpEntity, Long> {
	Page<EmpEntity> findByOrderById(Pageable pageable);
	
	/*Page<EmpEntity> findByContractAndDivisionOrderByDivision(String contract, String division, 
			 Pageable pageable);*/
	
	
	Page<EmpEntity> findByEmpNameAndEmpCodeAndDepartmentIdOrderByEmpCode(String emp_name, String emp_code, 
			long sales_department, Pageable pageable);
	int countByEmpNameAndEmpCodeAndDepartmentId(String emp_name, String emp_code, 
			long sales_department);
	
	Page<EmpEntity> findByEmpNameOrderByEmpCode(String emp_name, Pageable pageable);
	int countByEmpName(String emp_name);
	
	Page<EmpEntity> findByEmpCodeOrderByEmpCode(String c, Pageable pageable);
	int countByEmpCode(String emp_code);
	
	Page<EmpEntity> findByDepartmentIdOrderByEmpCode(long sales_department, Pageable pageable);
	int countByDepartmentId(long sales_department);
	
	Page<EmpEntity> findByEmpNameAndEmpCodeOrderByEmpCode(String emp_name, String emp_code, 
			 Pageable pageable);
	int countByEmpNameAndEmpCode(String emp_name, String emp_code);
	
	Page<EmpEntity> findByEmpNameAndDepartmentIdOrderByEmpCode(String emp_name, 
			long sales_department, Pageable pageable);
	int countByEmpNameAndDepartmentId(String emp_name, 
			long sales_department);
	
	Page<EmpEntity> findByEmpCodeAndDepartmentIdOrderByEmpCode(String emp_code, 
			long sales_department, Pageable pageable);
	int countByEmpCodeAndDepartmentId(String emp_code, 
			long sales_department);
	
	
}
