package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyEmpTicketRepository;
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;

@Service(value = "myEmpTicketServiceImpl")
@Transactional(readOnly = true)
public class MyEmpTicketServiceImpl implements MyEmpTicketService{
	@Autowired
	private MyEmpTicketRepository myEmpTicketRepository;

	@Override
	public int countAll() {
		
		return (int)myEmpTicketRepository.count();
	}

	@Override
	public List<EmpTicketEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByOrderByWeightDescModifytimeDesc(pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<EmpTicketEntity> findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName, String empCode,String division, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(empName, empCode, division, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(empName, empCode, division);
	}
	
	@Override
	public List<EmpTicketEntity> findByEmpEntity_empNameOrderByWeightDescModifytimeDesc(String empName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_empNameOrderByWeightDescModifytimeDesc(empName, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empName(String empName) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_empName(empName );
	}
	
	@Override
	public List<EmpTicketEntity> findByEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empCode, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_empCodeOrderByWeightDescModifytimeDesc(empCode, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empCode(String empCode) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_empCode(empCode );
	}
	
	@Override
	public List<EmpTicketEntity> findByEmpEntity_divisionOrderByWeightDescModifytimeDesc(String division, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_divisionOrderByWeightDescModifytimeDesc(division, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_division(String division) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_division(division );
	}

	@Override
	public List<EmpTicketEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_empNameAndEmpEntity_empCodeOrderByWeightDescModifytimeDesc(empName, empCode, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_empNameAndEmpEntity_empCode(empName, empCode);
	}

	@Override
	public List<EmpTicketEntity> findByEmpEntity_empNameAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName,
			String division, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_empNameAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(empName, division, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;	
		}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_division(String empName, String division) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_empNameAndEmpEntity_division(empName, division);
	}

	@Override
	public List<EmpTicketEntity> findByEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empCode,
			String division, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketEntity> pages = myEmpTicketRepository.findByEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(empCode, division, pageRequest);
		List<EmpTicketEntity> infos = pages.getContent();
		return infos;	
	}

	@Override
	public int countByEmpEntity_empCodeAndEmpEntity_division(String empCode, String division) {
		// TODO Auto-generated method stub
		return myEmpTicketRepository.countByEmpEntity_empCodeAndEmpEntity_division(empCode, division);
	}
}
