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
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username,
			String clientId, String empName, String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( username,
				 clientId,  empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String username, String clientId, String empName,
			String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode( username,  clientId,  empName,
				 empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String username, String clientId,
			String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime( username,
				 clientId,  empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empName(String username, String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empName(username, clientId, empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime( username,
				 clientId,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCode(String username, String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCode(username, clientId, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( username,
				empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCode(String username, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCode(username, empName, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String clientId, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( clientId,
				empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String clientId, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(clientId, empName, empCode);
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
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empNameOrderByRecordTime(String username, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndEmpEntity_empNameOrderByRecordTime( username,
				empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndEmpEntity_empName(String username, String empName) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUserEntity_usernameAndEmpEntity_empName(username, empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empCodeOrderByRecordTime(String username, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_usernameAndEmpEntity_empCodeOrderByRecordTime( username,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndEmpEntity_empCode(String username, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_usernameAndEmpEntity_empCode(username, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String clientId, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime( clientId,
				empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndEmpEntity_empName(String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndEmpEntity_empName( clientId,
				empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String clientId, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime( clientId,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndEmpEntity_empCode(String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndEmpEntity_empCode( clientId,
				empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String empName, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( empName,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByEmpEntity_empNameAndEmpEntity_empCode( empName,
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
	public List<UserVoteEmpResultEntity> findByEmpEntity_empNameOrderByRecordTime(String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByEmpEntity_empNameOrderByRecordTime( empName, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empName(String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByEmpEntity_empName( empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByEmpEntity_empCodeOrderByRecordTime(String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByEmpEntity_empCodeOrderByRecordTime( empCode, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empCode(String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByEmpEntity_empCode( empCode);
	}
}
