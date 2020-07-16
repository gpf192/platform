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
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByTypeOrderById(true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceOrderByGainTime(String username, String clientId,
			String sourceId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientNameAndUserEntity_clientIdAndVotesSourceAndTypeOrderByGainTime(username, clientId, sourceId, true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(String username, String clientId, String surceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientNameAndUserEntity_clientIdAndVotesSourceAndType(username, clientId, surceId, true);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameOrderByGainTime(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientNameAndTypeOrderByGainTime(username,true ,pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_username(String username) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientNameAndType(username,true);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_clientIdOrderByGainTime(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientIdAndTypeOrderByGainTime(clientId, true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientId(String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientIdAndType(clientId, true);
	}

	@Override
	public List<UserTicketRecordEntity> findByVotesSourceOrderByGainTime(String sourceId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByVotesSourceAndTypeOrderByGainTime(sourceId, true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByVotesSource(String surceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByVotesSourceAndType(surceId, true);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameAndUserEntity_clientIdOrderByGainTime(String username, String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientNameAndUserEntity_clientIdAndTypeOrderByGainTime(username, clientId, true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientNameAndUserEntity_clientIdAndType(username, clientId, true);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_usernameAndVotesSourceOrderByGainTime(String username, String sourceId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientNameAndVotesSourceAndTypeOrderByGainTime(username, sourceId, true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_usernameAndVotesSource(String username, String sourceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientNameAndVotesSourceAndType(username, sourceId, true);
	}

	@Override
	public List<UserTicketRecordEntity> findByUserEntity_clientIdAndVotesSourceOrderByGainTime(String clientId, String sourceId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserTicketRecordEntity> pages = myUserVoteRepository.findByUserEntity_clientIdAndVotesSourceAndTypeOrderByGainTime(clientId, sourceId, true, pageRequest);
		List<UserTicketRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUserEntity_clientIdAndVotesSource(String clientId, String sourceId) {
		// TODO Auto-generated method stub
		return myUserVoteRepository.countByUserEntity_clientIdAndVotesSourceAndType(clientId, sourceId, true);
	}

}
