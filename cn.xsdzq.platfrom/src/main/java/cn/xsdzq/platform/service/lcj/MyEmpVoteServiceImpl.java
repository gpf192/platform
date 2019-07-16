package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.EmpTicketRecordRepository;
import cn.xsdzq.platform.dao.lcj.MyEmpVoteRepository;
import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;

@Service(value = "myEmpVoteServiceImpl")
@Transactional(readOnly = true)
public class MyEmpVoteServiceImpl implements MyEmpVoteService{
	@Autowired
	private MyEmpVoteRepository myEmpVoteRepository;
	
	@Autowired
	private EmpTicketRecordRepository empTicketRecordRepository;

	@Override
	public int countAll() {
		
		return (int)myEmpVoteRepository.count();
	}

	@Override
	public List<EmpTicketRecordEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByOrderByRecordTime(pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(String empName, String empCode,String division, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(empName, empCode, division, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(empName, empCode, division);
	}
	
	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_empNameOrderByRecordTimeDesc(String empName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_empNameOrderByRecordTimeDesc(empName, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empName(String empName) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_empName(empName );
	}
	
	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_empCodeOrderByRecordTimeDesc(String empCode, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_empCodeOrderByRecordTimeDesc(empCode, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empCode(String empCode) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_empCode(empCode );
	}
	
	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_divisionOrderByRecordTimeDesc(String division, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_divisionOrderByRecordTimeDesc(division, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_division(String division) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_division(division );
	}

	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTimeDesc(String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTimeDesc(empName, empCode, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_empNameAndEmpEntity_empCode(empName, empCode);
	}

	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_empNameAndEmpEntity_divisionOrderByRecordTimeDesc(String empName,
			String division, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_empNameAndEmpEntity_divisionOrderByRecordTimeDesc(empName, division, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;	
		}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_division(String empName, String division) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_empNameAndEmpEntity_division(empName, division);
	}

	@Override
	public List<EmpTicketRecordEntity> findByEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(String empCode,
			String division, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpTicketRecordEntity> pages = myEmpVoteRepository.findByEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(empCode, division, pageRequest);
		List<EmpTicketRecordEntity> infos = pages.getContent();
		return infos;	
	}

	@Override
	public int countByEmpEntity_empCodeAndEmpEntity_division(String empCode, String division) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpEntity_empCodeAndEmpEntity_division(empCode, division);
	}

}
