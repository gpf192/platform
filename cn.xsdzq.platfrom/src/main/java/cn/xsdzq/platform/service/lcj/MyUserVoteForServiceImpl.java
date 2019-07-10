package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyUserVoteForRepository;
import cn.xsdzq.platform.entity.lcj.UserVoteForEntity;

@Service(value = "myUserVoteForServiceImpl")
@Transactional(readOnly = true)
public class MyUserVoteForServiceImpl implements MyUserVoteForService{
	@Autowired
	private MyUserVoteForRepository myUserVoteForRepository;

	@Override
	public int countAll() {
		
		return (int)myUserVoteForRepository.count();
	}

	@Override
	public List<UserVoteForEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByOrderById(pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndClientIdAndEmpNameAndEmpCodeOrderByVoteTime(String username,
			String clientId, String empName, String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndClientIdAndEmpNameAndEmpCodeOrderByVoteTime( username,
				 clientId,  empName,  empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndClientIdAndEmpNameAndEmpCode(String username, String clientId, String empName,
			String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUsernameAndClientIdAndEmpNameAndEmpCode( username,  clientId,  empName,
				 empCode);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndClientIdAndEmpNameOrderByVoteTime(String username, String clientId,
			String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndClientIdAndEmpNameOrderByVoteTime( username,
				 clientId,  empName,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndClientIdAndEmpName(String username, String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUsernameAndClientIdAndEmpName(username, clientId, empName);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndClientIdAndEmpCodeOrderByVoteTime(String username, String clientId,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndClientIdAndEmpCodeOrderByVoteTime( username,
				 clientId,  empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndClientIdAndEmpCode(String username, String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUsernameAndClientIdAndEmpCode(username, clientId, empCode);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndEmpNameAndEmpCodeOrderByVoteTime(String username, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndEmpNameAndEmpCodeOrderByVoteTime( username,
				empName,  empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndEmpNameAndEmpCode(String username, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUsernameAndEmpNameAndEmpCode(username, empName, empCode);
	}

	@Override
	public List<UserVoteForEntity> findByClientIdAndEmpNameAndEmpCodeOrderByVoteTime(String clientId, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByClientIdAndEmpNameAndEmpCodeOrderByVoteTime( clientId,
				empName,  empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndEmpNameAndEmpCode(String clientId, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByClientIdAndEmpNameAndEmpCode(clientId, empName, empCode);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndClientIdOrderByVoteTime(String username, String clientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndClientIdOrderByVoteTime( username,
				clientId,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndClientId(String username, String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUsernameAndClientId(username, clientId);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndEmpNameOrderByVoteTime(String username, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndEmpNameOrderByVoteTime( username,
				empName,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndEmpName(String username, String empName) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUsernameAndEmpName(username, empName);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameAndEmpCodeOrderByVoteTime(String username, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameAndEmpCodeOrderByVoteTime( username,
				empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndEmpCode(String username, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUsernameAndEmpCode(username, empCode);
	}

	@Override
	public List<UserVoteForEntity> findByClientIdAndEmpNameOrderByVoteTime(String clientId, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByClientIdAndEmpNameOrderByVoteTime( clientId,
				empName,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndEmpName(String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByClientIdAndEmpName( clientId,
				empName);
	}

	@Override
	public List<UserVoteForEntity> findByClientIdAndEmpCodeOrderByVoteTime(String clientId, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByClientIdAndEmpCodeOrderByVoteTime( clientId,
				empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndEmpCode(String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByClientIdAndEmpCode( clientId,
				empCode);
	}

	@Override
	public List<UserVoteForEntity> findByEmpNameAndEmpCodeOrderByVoteTime(String empName, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByEmpNameAndEmpCodeOrderByVoteTime( empName,
				empCode,pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpNameAndEmpCode(String empName, String empCode) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByEmpNameAndEmpCode( empName,
				empCode);
	}

	@Override
	public List<UserVoteForEntity> findByUsernameOrderByVoteTime(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByUsernameOrderByVoteTime( username, pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsername(String username) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUsername( username);
	}

	@Override
	public List<UserVoteForEntity> findByClientIdOrderByVoteTime(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByClientIdOrderByVoteTime( clientId, pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByClientId( clientId);
	}

	@Override
	public List<UserVoteForEntity> findByEmpNameOrderByVoteTime(String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByEmpNameOrderByVoteTime( empName, pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpName(String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByEmpName( empName);
	}

	@Override
	public List<UserVoteForEntity> findByEmpCodeOrderByVoteTime(String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteForEntity> pages = myUserVoteForRepository.findByEmpCodeOrderByVoteTime( empCode, pageRequest);
		List<UserVoteForEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpCode(String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByEmpCode( empCode);
	}
}
