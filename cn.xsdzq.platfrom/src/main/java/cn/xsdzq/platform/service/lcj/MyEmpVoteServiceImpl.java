package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyEmpVoteRepository;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;

@Service(value = "myEmpVoteServiceImpl")
@Transactional(readOnly = true)
public class MyEmpVoteServiceImpl implements MyEmpVoteService{
	@Autowired
	private MyEmpVoteRepository myEmpVoteRepository;

	@Override
	public int countAll() {
		
		return (int)myEmpVoteRepository.count();
	}

	@Override
	public List<EmpVoteEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpVoteEntity> pages = myEmpVoteRepository.findByOrderById(pageRequest);
		List<EmpVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<EmpVoteEntity> findByEmpNameAndVoteFromUserOrderByAccount(String empName, String voteFromUser, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpVoteEntity> pages = myEmpVoteRepository.findByEmpNameAndVoteFromUserOrderByAccount(empName, voteFromUser, pageRequest);
		List<EmpVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpNameAndVoteFromUser(String empName, String voteFromUser) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpNameAndVoteFromUser(empName, voteFromUser);
	}

	@Override
	public List<EmpVoteEntity> findByEmpNameOrderByAccount(String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpVoteEntity> pages = myEmpVoteRepository.findByEmpNameOrderByAccount(empName,  pageRequest);
		List<EmpVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpName(String empName) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByEmpName(empName);
	}

	@Override
	public List<EmpVoteEntity> findByVoteFromUserOrderByAccount(String voteFromUser, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpVoteEntity> pages = myEmpVoteRepository.findByVoteFromUserOrderByAccount(voteFromUser,  pageRequest);
		List<EmpVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByVoteFromUser(String voteFromUser) {
		// TODO Auto-generated method stub
		return myEmpVoteRepository.countByVoteFromUser(voteFromUser);
	}
}
