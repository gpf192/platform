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
	
	
	Page<EmpEntity> findByEmpNameAndEmpCodeAndDepartmentCodeOrderByEmpCode(String emp_name, String emp_code, 
			String sales_department, Pageable pageable);
	int countByEmpNameAndEmpCodeAndDepartmentCode(String emp_name, String emp_code, 
			String sales_department);
	
	Page<EmpEntity> findByEmpNameOrderByEmpCode(String emp_name, Pageable pageable);
	int countByEmpName(String emp_name);
	
	Page<EmpEntity> findByEmpCodeOrderByEmpCode(String c, Pageable pageable);
	int countByEmpCode(String emp_code);
	
	Page<EmpEntity> findByDepartmentCodeOrderByEmpCode(String sales_department, Pageable pageable);
	int countByDepartmentCode(String sales_department);
	
	Page<EmpEntity> findByEmpNameAndEmpCodeOrderByEmpCode(String emp_name, String emp_code, 
			 Pageable pageable);
	int countByEmpNameAndEmpCode(String emp_name, String emp_code);
	
	Page<EmpEntity> findByEmpNameAndDepartmentCodeOrderByEmpCode(String emp_name, 
			String sales_department, Pageable pageable);
	int countByEmpNameAndDepartmentCode(String emp_name, 
			String sales_department);
	
	Page<EmpEntity> findByEmpCodeAndDepartmentCodeOrderByEmpCode(String emp_code, 
			String sales_department, Pageable pageable);
	int countByEmpCodeAndDepartmentCode(String emp_code, 
			String sales_department);
	
	
}
