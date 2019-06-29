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
	
	
	Page<EmpEntity> findByEmpNameAndEmpCodeAndSalesDepartmentOrderByEmpCode(String emp_name, String emp_code, 
			String sales_department, Pageable pageable);
	int countByEmpNameAndEmpCodeAndSalesDepartment(String emp_name, String emp_code, 
			String sales_department);
	
	Page<EmpEntity> findByEmpNameOrderByEmpCode(String emp_name, Pageable pageable);
	int countByEmpName(String emp_name);
	
	Page<EmpEntity> findByEmpCodeOrderByEmpCode(String c, Pageable pageable);
	int countByEmpCode(String emp_code);
	
	Page<EmpEntity> findBySalesDepartmentOrderByEmpCode(String sales_department, Pageable pageable);
	int countBySalesDepartment(String sales_department);
	
	Page<EmpEntity> findByEmpNameAndEmpCodeOrderByEmpCode(String emp_name, String emp_code, 
			 Pageable pageable);
	int countByEmpNameAndEmpCode(String emp_name, String emp_code);
	
	Page<EmpEntity> findByEmpNameAndSalesDepartmentOrderByEmpCode(String emp_name, 
			String sales_department, Pageable pageable);
	int countByEmpNameAndSalesDepartment(String emp_name, 
			String sales_department);
	
	Page<EmpEntity> findByEmpCodeAndSalesDepartmentOrderByEmpCode(String emp_code, 
			String sales_department, Pageable pageable);
	int countByEmpCodeAndSalesDepartment(String emp_code, 
			String sales_department);
	
	
}
