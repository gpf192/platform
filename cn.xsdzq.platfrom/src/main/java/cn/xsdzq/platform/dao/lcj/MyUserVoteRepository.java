package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyUserVoteRepository extends PagingAndSortingRepository<UserVoteEntity, Long> {
	Page<UserVoteEntity> findByOrderById(Pageable pageable);
	
	Page<UserVoteEntity> findByUsernameAndAccountAndSourceIdOrderByAccount(String username, String account, String sourceId, Pageable pageable);
	int countByUsernameAndAccountAndSourceId(String username, String account, String sourceId);
	
	Page<UserVoteEntity> findByUsernameOrderByAccount(String username, Pageable pageable);
	int countByUsername(String username);
	
	Page<UserVoteEntity> findByAccountOrderByAccount(String account, Pageable pageable);
	int countByAccount(String account);
	
	Page<UserVoteEntity> findBySourceIdOrderByAccount(String sourceId, Pageable pageable);
	int countBySourceId(String sourceId);
	
	Page<UserVoteEntity> findByUsernameAndAccountOrderByAccount(String username, String account, Pageable pageable);
	int countByUsernameAndAccount(String username, String account);
	
	Page<UserVoteEntity> findByUsernameAndSourceIdOrderByAccount(String username, String sourceId, Pageable pageable);
	int countByUsernameAndSourceId(String username, String sourceId);
	
	Page<UserVoteEntity> findByAccountAndSourceIdOrderByAccount(String account, String sourceId, Pageable pageable);
	int countByAccountAndSourceId(String account, String sourceId);
	
}
