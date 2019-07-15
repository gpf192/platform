package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

public interface MyUserVoteService {
	int countAll();
	List<UserTicketRecordEntity> getAll(int pageNumber, int pageSize);
	
	List<UserTicketRecordEntity>findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceGainTime(String username, String clientId, String sourceId, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(String username, String clientId, String sourceId);
	
	List<UserTicketRecordEntity>findByUserEntity_usernameGainTime(String username, int pageNumber, int pageSize);
	int countByUserEntity_username(String username);
	
	List<UserTicketRecordEntity>findByUserEntity_clientIdGainTime( String clientId, int pageNumber, int pageSize);
	int countByUserEntity_clientId( String clientId);
	
	List<UserTicketRecordEntity>findByVotesSourceGainTime(String sourceId, int pageNumber, int pageSize);
	int countByVotesSource(String sourceId);
	
	List<UserTicketRecordEntity>findByUserEntity_usernameAndUserEntity_clientIdGainTime(String username, String clientId , int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId );
	
	List<UserTicketRecordEntity>findByUserEntity_usernameAndVotesSourceGainTime(String username , String sourceId, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndVotesSource(String username , String sourceId);
	
	List<UserTicketRecordEntity>findByUserEntity_clientIdAndVotesSourceGainTime(String clientId, String sourceId, int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndVotesSource(String clientId, String sourceId);
}
