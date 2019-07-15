package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyUserVoteForRepository;
import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

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
	public List<UserVoteEmpResultEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByOrderById(pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String username,
			String clientId, String empName, String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( username,
				 clientId,  empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(String username, String clientId, String empName,
			String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode( username,  clientId,  empName,
				 empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(String username, String clientId,
			String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime( username,
				 clientId,  empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empName(String username, String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empName(username, clientId, empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(String username, String clientId,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime( username,
				 clientId,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCode(String username, String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCode(username, clientId, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String username, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( username,
				empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCode(String username, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCode(username, empName, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String clientId, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( clientId,
				empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(String clientId, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(clientId, empName, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime( username,
				clientId,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientId(username, clientId);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_empNameOrderByRecordTime(String username, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_empNameOrderByRecordTime( username,
				empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_empName(String username, String empName) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_empName(username, empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_empCodeOrderByRecordTime(String username, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_empCodeOrderByRecordTime( username,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_empCode(String username, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_empCode(username, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(String clientId, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime( clientId,
				empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndUserEntity_empName(String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndUserEntity_empName( clientId,
				empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(String clientId, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime( clientId,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndUserEntity_empCode(String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndUserEntity_empCode( clientId,
				empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String empName, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( empName,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_empNameAndUserEntity_empCode(String empName, String empCode) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUserEntity_empNameAndUserEntity_empCode( empName,
				empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameOrderByRecordTime(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameOrderByRecordTime( username, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_username(String username) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUserEntity_username( username);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdOrderByRecordTime(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdOrderByRecordTime( clientId, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientId(String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientId( clientId);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_empNameOrderByRecordTime(String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_empNameOrderByRecordTime( empName, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_empName(String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_empName( empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_empCodeOrderByRecordTime(String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_empCodeOrderByRecordTime( empCode, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_empCode(String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_empCode( empCode);
	}
}
