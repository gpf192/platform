package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

public interface MyUserVoteRepository extends PagingAndSortingRepository<UserTicketRecordEntity, Long> {
	Page<UserTicketRecordEntity> findByOrderById(Pageable pageable);
	
	Page<UserTicketRecordEntity> findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceGainTime(String username, String clientId, String sourceId, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(String username, String clientId, String sourceId);
	
	Page<UserTicketRecordEntity> findByUserEntity_usernameGainTime(String username, Pageable pageable);
	int countByUserEntity_username(String username);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdGainTime(String clientId, Pageable pageable);
	int countByUserEntity_clientId(String clientId);
	
	Page<UserTicketRecordEntity> findByVotesSourceGainTime(String sourceId, Pageable pageable);
	int countByVotesSource(String sourceId);
	
	Page<UserTicketRecordEntity> findByUserEntity_usernameAndUserEntity_clientIdGainTime(String username, String clientId, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId);
	
	Page<UserTicketRecordEntity> findByUserEntity_usernameAndVotesSourceGainTime(String username, String sourceId, Pageable pageable);
	int countByUserEntity_usernameAndVotesSource(String username, String sourceId);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdAndVotesSourceGainTime(String clientId, String sourceId, Pageable pageable);
	int countByUserEntity_clientIdAndVotesSource(String clientId, String sourceId);
	
}
