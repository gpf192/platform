package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;

public interface MyEmpTicketService {
	//员工得票总数记录
		int countAll();
		List<EmpTicketEntity> getAll(int pageNumber, int pageSize);
		
		List<EmpTicketEntity>findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName, String empCode,String division, int pageNumber, int pageSize);
		int countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division);
		
		List<EmpTicketEntity>findByEmpEntity_empNameOrderByWeightDescModifytimeDesc(String empName , int pageNumber, int pageSize);
		int countByEmpEntity_empName(String empName);
		
		List<EmpTicketEntity>findByEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empCode , int pageNumber, int pageSize);
		int countByEmpEntity_empCode(String division);
		
		List<EmpTicketEntity>findByEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empCode , int pageNumber, int pageSize);
		int countByEmpEntity_division(String division);
		
		List<EmpTicketEntity>findByEmpEntity_empNameAndEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empName, String empCode, int pageNumber, int pageSize);
		int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode);
		
		List<EmpTicketEntity>findByEmpEntity_empNameAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName, String division, int pageNumber, int pageSize);
		int countByEmpEntity_empNameAndEmpEntity_division(String empName, String division);
		
		List<EmpTicketEntity>findByEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empCode, String division, int pageNumber, int pageSize);
		int countByEmpEntity_empCodeAndEmpEntity_division(String empCode, String division);
}
