package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

public interface MyUserVoteService {
	int countAll();
	List<UserTicketRecordEntity> getAll(int pageNumber, int pageSize);
	
	List<UserTicketRecordEntity>findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceOrderByGainTime(String username, String clientId, String sourceId, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(String username, String clientId, String sourceId);
	
	List<UserTicketRecordEntity>findByUserEntity_usernameOrderByGainTime(String username, int pageNumber, int pageSize);
	int countByUserEntity_username(String username);
	
	List<UserTicketRecordEntity>findByUserEntity_clientIdOrderByGainTime( String clientId, int pageNumber, int pageSize);
	int countByUserEntity_clientId( String clientId);
	
	List<UserTicketRecordEntity>findByVotesSourceOrderByGainTime(String sourceId, int pageNumber, int pageSize);
	int countByVotesSource(String sourceId);
	
	List<UserTicketRecordEntity>findByUserEntity_usernameAndUserEntity_clientIdOrderByGainTime(String username, String clientId , int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId );
	
	List<UserTicketRecordEntity>findByUserEntity_usernameAndVotesSourceOrderByGainTime(String username , String sourceId, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndVotesSource(String username , String sourceId);
	
	List<UserTicketRecordEntity>findByUserEntity_clientIdAndVotesSourceOrderByGainTime(String clientId, String sourceId, int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndVotesSource(String clientId, String sourceId);
}
