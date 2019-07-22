package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

public interface MyUserVoteRepository extends PagingAndSortingRepository<UserTicketRecordEntity, Long> {
	Page<UserTicketRecordEntity> findByOrderById(Pageable pageable);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndUserEntity_clientIdAndVotesSourceOrderByGainTime(String username, String clientId, String sourceId, Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientIdAndVotesSource(String username, String clientId, String sourceId);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameOrderByGainTime(String username, Pageable pageable);
	int countByUserEntity_clientName(String username);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdOrderByGainTime(String clientId, Pageable pageable);
	int countByUserEntity_clientId(String clientId);
	
	Page<UserTicketRecordEntity> findByVotesSourceOrderByGainTime(String sourceId, Pageable pageable);
	int countByVotesSource(String sourceId);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndUserEntity_clientIdOrderByGainTime(String username, String clientId, Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientId(String username, String clientId);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndVotesSourceOrderByGainTime(String username, String sourceId, Pageable pageable);
	int countByUserEntity_clientNameAndVotesSource(String username, String sourceId);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdAndVotesSourceOrderByGainTime(String clientId, String sourceId, Pageable pageable);
	int countByUserEntity_clientIdAndVotesSource(String clientId, String sourceId);
	
}
