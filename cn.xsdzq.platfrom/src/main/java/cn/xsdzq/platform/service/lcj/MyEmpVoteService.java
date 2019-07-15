package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;

public interface MyEmpVoteService {
	int countAll();
	List<EmpTicketRecordEntity> getAll(int pageNumber, int pageSize);
	
	List<EmpTicketRecordEntity>findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(String empName, String empCode,String division, int pageNumber, int pageSize);
	int countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division);
	
	List<EmpTicketRecordEntity>findByEmpEntity_empNameOrderByRecordTimeDesc(String empName , int pageNumber, int pageSize);
	int countByEmpEntity_empName(String empName);
	
	List<EmpTicketRecordEntity>findByEmpEntity_empCodeOrderByRecordTimeDesc(String empCode , int pageNumber, int pageSize);
	int countByEmpEntity_empCode(String division);
	
	List<EmpTicketRecordEntity>findByEmpEntity_divisionOrderByRecordTimeDesc(String empCode , int pageNumber, int pageSize);
	int countByEmpEntity_division(String division);
	
	List<EmpTicketRecordEntity>findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTimeDesc(String empName, String empCode, int pageNumber, int pageSize);
	int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode);
	
	List<EmpTicketRecordEntity>findByEmpEntity_empNameAndEmpEntity_divisionOrderByRecordTimeDesc(String empName, String division, int pageNumber, int pageSize);
	int countByEmpEntity_empNameAndEmpEntity_division(String empName, String division);
	
	List<EmpTicketRecordEntity>findByEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(String empCode, String division, int pageNumber, int pageSize);
	int countByEmpEntity_empCodeAndEmpEntity_division(String empCode, String division);
	
}
