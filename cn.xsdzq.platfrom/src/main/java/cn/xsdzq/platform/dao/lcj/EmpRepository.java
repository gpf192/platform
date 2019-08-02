package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;

@Repository
public interface EmpRepository {
	List<EmpEntity> getAllEmp();
	EmpEntity getEmpByEmpCode(String empCode);
	void deleteEmp(EmpEntity entity);

	void addEmp(EmpEntity entity);
	void modifyEmp(EmpEntity entity);
	void addEmpTicket(EmpTicketEntity entity);
	void modifyEmpTicket(EmpTicketEntity entity);
	EmpTicketEntity getEmpTicketByEmpId(String id);
	void deleteEmpTicket(EmpTicketEntity entity);
}
