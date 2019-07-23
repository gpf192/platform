package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;

public interface MyEmpTicketRepository  extends PagingAndSortingRepository<EmpTicketEntity, Long> {
	
	Page<EmpTicketEntity> findByOrderByWeightDescModifytimeDesc(Pageable pageable);
	
	Page<EmpTicketEntity> findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName, String empCode,String division, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division);
	
	Page<EmpTicketEntity> findByEmpEntity_empNameOrderByWeightDescModifytimeDesc(String empName, Pageable pageable);
	int countByEmpEntity_empName(String empName);
	
	Page<EmpTicketEntity> findByEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empCode, Pageable pageable);
	int countByEmpEntity_empCode(String empCode);
	
	Page<EmpTicketEntity> findByEmpEntity_divisionOrderByWeightDescModifytimeDesc(String division, Pageable pageable);
	int countByEmpEntity_division(String division);
	
	Page<EmpTicketEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empName, String empCode, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode );
	
	Page<EmpTicketEntity> findByEmpEntity_empNameAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName,String division, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_division(String empName,String division);
	
	Page<EmpTicketEntity> findByEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc( String empCode,String division, Pageable pageable);
	int countByEmpEntity_empCodeAndEmpEntity_division( String empCode,String division);	
}
