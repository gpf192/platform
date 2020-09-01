package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;

public interface MyUserVoteRepository extends PagingAndSortingRepository<UserTicketRecordEntity, Long> {
	Page<UserTicketRecordEntity> findByTypeOrderByGainTimeDesc(boolean type,Pageable pageable);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdAndVotesSourceAndTypeOrderByGainTimeDesc(String username, String clientId, String sourceId, boolean type, Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdAndVotesSourceAndType(String username, String clientId, String sourceId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameLikeAndTypeOrderByGainTimeDesc(String username, boolean type, Pageable pageable);
	int countByUserEntity_clientNameLikeAndType(String username,boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdAndTypeOrderByGainTimeDesc(String clientId, boolean type, Pageable pageable);
	int countByUserEntity_clientIdAndType(String clientId,boolean type);
	
	Page<UserTicketRecordEntity> findByVotesSourceAndTypeOrderByGainTimeDesc(String sourceId, boolean type, Pageable pageable);
	int countByVotesSourceAndType(String sourceId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdAndTypeOrderByGainTimeDesc(String username, String clientId, boolean type, Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdAndType(String username, String clientId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientNameLikeAndVotesSourceAndTypeOrderByGainTimeDesc(String username, String sourceId, boolean type, Pageable pageable);
	int countByUserEntity_clientNameLikeAndVotesSourceAndType(String username, String sourceId, boolean type);
	
	Page<UserTicketRecordEntity> findByUserEntity_clientIdAndVotesSourceAndTypeOrderByGainTimeDesc(String clientId, String sourceId, boolean type, Pageable pageable);
	int countByUserEntity_clientIdAndVotesSourceAndType(String clientId, String sourceId, boolean type);
	
}
