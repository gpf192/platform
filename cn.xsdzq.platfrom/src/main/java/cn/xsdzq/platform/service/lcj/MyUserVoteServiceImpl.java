package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyUserVoteRepository;
import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

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
	public List<UserTicketRecordEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByOrderById(pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceGainTime(String username, String clientId,
			String sourceId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceGainTime(username, clientId, sourceId, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(String username, String clientId, String surceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(username, clientId, surceId);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameGainTime(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_usernameGainTime(username, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_username(String username) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_username(username);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_clientIdGainTime(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientIdGainTime(clientId, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientId(String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientId(clientId);
	}

	@Override
	public List<UserTicketRecordEntity> findByVotesSourceGainTime(String sourceId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByVotesSourceGainTime(sourceId, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByVotesSource(String surceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByVotesSource(surceId);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameAndUserEntity_clientIdGainTime(String username, String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_usernameAndUserEntity_clientIdGainTime(username, clientId, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_usernameAndUserEntity_clientId(username, clientId);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameAndVotesSourceGainTime(String username, String sourceId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_usernameAndVotesSourceGainTime(username, sourceId, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndVotesSource(String username, String sourceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_usernameAndVotesSource(username, sourceId);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_clientIdAndVotesSourceGainTime(String clientId, String sourceId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientIdAndVotesSourceGainTime(clientId, sourceId, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndVotesSource(String clientId, String sourceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientIdAndVotesSource(clientId, sourceId);
	}

}
