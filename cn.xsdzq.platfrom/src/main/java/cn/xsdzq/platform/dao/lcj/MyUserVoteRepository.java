package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

public interface MyUserVoteRepository extends PagingAndSortingRepository<UserTicketRecordEntity, Long> {
	Page<UserTicketRecordEntity> findByTypeOrderById(boolean type,Pageable pageable);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndUserEntity_clientIdAndVotesSourceAndTypeOrderByGainTime(String username, String clientId, String sourceId, boolean type, Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientIdAndVotesSourceAndType(String username, String clientId, String sourceId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndTypeOrderByGainTime(String username, boolean type, Pageable pageable);
	int countByUserEntity_clientNameAndType(String username,boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdAndTypeOrderByGainTime(String clientId, boolean type, Pageable pageable);
	int countByUserEntity_clientIdAndType(String clientId,boolean type);
	
	Page<UserTicketRecordEntity> findByVotesSourceAndTypeOrderByGainTime(String sourceId, boolean type, Pageable pageable);
	int countByVotesSourceAndType(String sourceId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndUserEntity_clientIdAndTypeOrderByGainTime(String username, String clientId, boolean type, Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientIdAndType(String username, String clientId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameAndVotesSourceAndTypeOrderByGainTime(String username, String sourceId, boolean type, Pageable pageable);
	int countByUserEntity_clientNameAndVotesSourceAndType(String username, String sourceId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdAndVotesSourceAndTypeOrderByGainTime(String clientId, String sourceId, boolean type, Pageable pageable);
	int countByUserEntity_clientIdAndVotesSourceAndType(String clientId, String sourceId, boolean type);
	
}
