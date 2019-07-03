package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyUserVoteService {
	int countAll();
	List<UserVoteEntity> getAll(int pageNumber, int pageSize);
	
	List<UserVoteEntity>findByUsernameAndAccountAndSourceIdOrderByAccount(String username, String account, String sourceId, int pageNumber, int pageSize);
	int countByUsernameAndAccountAndSourceId(String username, String account, String sourceId);
	
	List<UserVoteEntity>findByUsernameOrderByAccount(String username, int pageNumber, int pageSize);
	int countByUsername(String username);
	
	List<UserVoteEntity>findByAccountOrderByAccount( String account, int pageNumber, int pageSize);
	int countByAccount( String account);
	
	List<UserVoteEntity>findBySourceIdOrderByAccount(String sourceId, int pageNumber, int pageSize);
	int countBySourceId(String sourceId);
	
	List<UserVoteEntity>findByUsernameAndAccountOrderByAccount(String username, String account , int pageNumber, int pageSize);
	int countByUsernameAndAccount(String username, String account );
	
	List<UserVoteEntity>findByUsernameAndSourceIdOrderByAccount(String username , String sourceId, int pageNumber, int pageSize);
	int countByUsernameAndSourceId(String username , String sourceId);
	
	List<UserVoteEntity>findByAccountAndSourceIdOrderByAccount(String account, String sourceId, int pageNumber, int pageSize);
	int countByAccountAndSourceId(String account, String sourceId);
}
