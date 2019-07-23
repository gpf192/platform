package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;

public interface MyEmpVoteRepository extends PagingAndSortingRepository<EmpTicketRecordEntity, Long> {
	//员工得票明细
	Page<EmpTicketRecordEntity> findByOrderByRecordTime(Pageable pageable);
	
	Page<EmpTicketRecordEntity> findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(String empName, String empCode,String division, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division);
	
	Page<EmpTicketRecordEntity> findByEmpEntity_empNameOrderByRecordTimeDesc(String empName, Pageable pageable);
	int countByEmpEntity_empName(String empName);
	
	Page<EmpTicketRecordEntity> findByEmpEntity_empCodeOrderByRecordTimeDesc(String empCode, Pageable pageable);
	int countByEmpEntity_empCode(String empCode);
	
	Page<EmpTicketRecordEntity> findByEmpEntity_divisionOrderByRecordTimeDesc(String division, Pageable pageable);
	int countByEmpEntity_division(String division);
	
	Page<EmpTicketRecordEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTimeDesc(String empName, String empCode, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode );
	
	Page<EmpTicketRecordEntity> findByEmpEntity_empNameAndEmpEntity_divisionOrderByRecordTimeDesc(String empName,String division, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_division(String empName,String division);
	
	Page<EmpTicketRecordEntity> findByEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc( String empCode,String division, Pageable pageable);
	int countByEmpEntity_empCodeAndEmpEntity_division( String empCode,String division);	

	
}
