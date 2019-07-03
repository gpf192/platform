package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyUserVoteRepository;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

@Service(value = "myUserVoteServiceImpl")
@Transactional(readOnly = true)
public class MyUserVoteServiceImpl implements MyUserVoteService{
	@Autowired
	private MyUserVoteRepository myUserVoteRepository;

	@Override
	public int countAll() {
		
		return (int)myUserVoteRepository.count();
	}

	@Override
	public List<UserVoteEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByOrderById(pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserVoteEntity> findByUsernameAndAccountAndSourceIdOrderByAccount(String username, String account,
			String sourceId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByUsernameAndAccountAndSourceIdOrderByAccount(username, account, sourceId, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndAccountAndSourceId(String username, String account, String surceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUsernameAndAccountAndSourceId(username, account, surceId);
	}

	@Override
	public List<UserVoteEntity> findByUsernameOrderByAccount(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByUsernameOrderByAccount(username, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsername(String username) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUsername(username);
	}

	@Override
	public List<UserVoteEntity> findByAccountOrderByAccount(String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByAccountOrderByAccount(account, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByAccount(String account) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByAccount(account);
	}

	@Override
	public List<UserVoteEntity> findBySourceIdOrderByAccount(String sourceId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findBySourceIdOrderByAccount(sourceId, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countBySourceId(String surceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countBySourceId(surceId);
	}

	@Override
	public List<UserVoteEntity> findByUsernameAndAccountOrderByAccount(String username, String account, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByUsernameAndAccountOrderByAccount(username, account, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndAccount(String username, String account) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUsernameAndAccount(username, account);
	}

	@Override
	public List<UserVoteEntity> findByUsernameAndSourceIdOrderByAccount(String username, String sourceId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByUsernameAndSourceIdOrderByAccount(username, sourceId, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndSourceId(String username, String sourceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUsernameAndSourceId(username, sourceId);
	}

	@Override
	public List<UserVoteEntity> findByAccountAndSourceIdOrderByAccount(String account, String sourceId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserVoteEntity> pages = myUserVoteRepository.findByAccountAndSourceIdOrderByAccount(account, sourceId, pageRequest);
		List<UserVoteEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByAccountAndSourceId(String account, String sourceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByAccountAndSourceId(account, sourceId);
	}

}
