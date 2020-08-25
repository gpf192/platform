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
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username,
			String clientId, String empName, String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc( username,
				 clientId,  empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String username, String clientId, String empName,
			String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCode( username,  clientId,  empName,
				 empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String username, String clientId,
			String empName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLikeOrderByRecordTimeDesc( username,
				 clientId,  empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empName(String username, String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLike(username, clientId, empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTimeDesc( username,
				 clientId,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCode(String username, String clientId, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empCode(username, clientId, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc( username,
				empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCode(String username, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientNameLikeAndEmpEntity_empNameLikeAndEmpEntity_empCode(username, empName, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String clientId, String empName,
			String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc( clientId,
				empName,  empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String clientId, String empName, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCode(clientId, empName, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndUserEntity_clientIdOrderByRecordTimeDesc( username,
				clientId,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientNameLikeAndUserEntity_clientId(username, clientId);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empNameOrderByRecordTime(String username, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndEmpEntity_empNameLikeOrderByRecordTimeDesc( username,
				empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndEmpEntity_empName(String username, String empName) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUserEntity_clientNameLikeAndEmpEntity_empNameLike(username, empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empCodeOrderByRecordTime(String username, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc( username,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndEmpEntity_empCode(String username, String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientNameLikeAndEmpEntity_empCode(username, empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String clientId, String empName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndEmpEntity_empNameLikeOrderByRecordTimeDesc( clientId,
				empName,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndEmpEntity_empName(String clientId, String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByUserEntity_clientIdAndEmpEntity_empNameLike( clientId,
				empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String clientId, String empCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTimeDesc( clientId,
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
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc( empName,
				empCode,pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empNameAndEmpEntity_empCode(String empName, String empCode) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByEmpEntity_empNameLikeAndEmpEntity_empCode( empName,
				empCode);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_usernameOrderByRecordTime(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientNameLikeOrderByRecordTimeDesc( username, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_username(String username) {
		// TODO Auto-generated method stub
		 return myUserVoteForRepository.countByUserEntity_clientNameLike( username);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByUserEntity_clientIdOrderByRecordTime(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByUserEntity_clientIdOrderByRecordTimeDesc( clientId, pageRequest);
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
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByEmpEntity_empNameLikeOrderByRecordTimeDesc( empName, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empName(String empName) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByEmpEntity_empNameLike( empName);
	}

	@Override
	public List<UserVoteEmpResultEntity> findByEmpEntity_empCodeOrderByRecordTime(String empCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEmpResultEntity> pages = myUserVoteForRepository.findByEmpEntity_empCodeOrderByRecordTimeDesc( empCode, pageRequest);
		List<UserVoteEmpResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEmpEntity_empCode(String empCode) {
		// TODO Auto-generated method stub
		return myUserVoteForRepository.countByEmpEntity_empCode( empCode);
	}
}
