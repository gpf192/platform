package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserVoteForEntity;

public interface MyUserVoteForService {
	int countAll();
	List<UserVoteForEntity> getAll(int pageNumber, int pageSize);
	
	List<UserVoteForEntity>findByUsernameAndClientIdAndEmpNameAndEmpCodeOrderByVoteTime(String username, String clientId, String empName, String empCode, int pageNumber, int pageSize);
	int countByUsernameAndClientIdAndEmpNameAndEmpCode(String username, String clientId, String empName, String empCode);
	
	List<UserVoteForEntity>findByUsernameAndClientIdAndEmpNameOrderByVoteTime(String username, String clientId, String empName , int pageNumber, int pageSize);
	int countByUsernameAndClientIdAndEmpName(String username, String clientId, String empName);
	
	List<UserVoteForEntity>findByUsernameAndClientIdAndEmpCodeOrderByVoteTime(String username, String clientId, String empCode, int pageNumber, int pageSize);
	int countByUsernameAndClientIdAndEmpCode(String username, String clientId , String empCode);
	
	List<UserVoteForEntity>findByUsernameAndEmpNameAndEmpCodeOrderByVoteTime(String username, String empName, String empCode, int pageNumber, int pageSize);
	int countByUsernameAndEmpNameAndEmpCode(String username , String empName, String empCode);
	
	List<UserVoteForEntity>findByClientIdAndEmpNameAndEmpCodeOrderByVoteTime( String clientId, String empName, String empCode, int pageNumber, int pageSize);
	int countByClientIdAndEmpNameAndEmpCode( String clientId, String empName, String empCode);
	
	List<UserVoteForEntity>findByUsernameAndClientIdOrderByVoteTime(String username, String clientId, int pageNumber, int pageSize);
	int countByUsernameAndClientId(String username, String clientId);
	
	List<UserVoteForEntity>findByUsernameAndEmpNameOrderByVoteTime(String username, String empName, int pageNumber, int pageSize);
	int countByUsernameAndEmpName(String username, String empName);
	
	List<UserVoteForEntity>findByUsernameAndEmpCodeOrderByVoteTime(String username,  String empCode, int pageNumber, int pageSize);
	int countByUsernameAndEmpCode(String username, String empCode);
	
	List<UserVoteForEntity>findByClientIdAndEmpNameOrderByVoteTime(String clientId, String empName,  int pageNumber, int pageSize);
	int countByClientIdAndEmpName(String clientId, String empName);
	
	List<UserVoteForEntity>findByClientIdAndEmpCodeOrderByVoteTime(String clientId , String empCode, int pageNumber, int pageSize);
	int countByClientIdAndEmpCode( String clientId, String empCode);
	
	List<UserVoteForEntity>findByEmpNameAndEmpCodeOrderByVoteTime(String empName , String empCode, int pageNumber, int pageSize);
	int countByEmpNameAndEmpCode( String empName, String empCode);
	
	List<UserVoteForEntity>findByUsernameOrderByVoteTime(String username , int pageNumber, int pageSize);
	int countByUsername( String username );
	
	List<UserVoteForEntity>findByClientIdOrderByVoteTime(String clientId , int pageNumber, int pageSize);
	int countByClientId( String clientId );
	
	List<UserVoteForEntity>findByEmpNameOrderByVoteTime(String empName , int pageNumber, int pageSize);
	int countByEmpName( String empName );
	
	List<UserVoteForEntity>findByEmpCodeOrderByVoteTime(String empCode , int pageNumber, int pageSize);
	int countByEmpCode( String empCode );
}
