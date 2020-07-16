package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

public interface MyUserVoteForService {
	int countAll();
	List<UserVoteEmpResultEntity> getAll(int pageNumber, int pageSize);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId, String empName, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String username, String clientId, String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String username, String clientId, String empName , int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empName(String username, String clientId, String empName);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCode(String username, String clientId , String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username, String empName, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCode(String username , String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( String clientId, String empName, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode( String clientId, String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndEmpEntity_empNameOrderByRecordTime(String username, String empName, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndEmpEntity_empName(String username, String empName);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndEmpEntity_empCodeOrderByRecordTime(String username,  String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndEmpEntity_empCode(String username, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String clientId, String empName,  int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndEmpEntity_empName(String clientId, String empName);
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String clientId , String empCode, int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndEmpEntity_empCode( String clientId, String empCode);
	
	List<UserVoteEmpResultEntity>findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String empName , String empCode, int pageNumber, int pageSize);
	int countByEmpEntity_empNameAndEmpEntity_empCode( String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameOrderByRecordTime(String username , int pageNumber, int pageSize);
	int countByUserEntity_username( String username );
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdOrderByRecordTime(String clientId , int pageNumber, int pageSize);
	int countByUserEntity_clientId( String clientId );
	
	List<UserVoteEmpResultEntity>findByEmpEntity_empNameOrderByRecordTime(String empName , int pageNumber, int pageSize);
	int countByEmpEntity_empName( String empName );
	
	List<UserVoteEmpResultEntity>findByEmpEntity_empCodeOrderByRecordTime(String empCode , int pageNumber, int pageSize);
	int countByEmpEntity_empCode( String empCode );
}
