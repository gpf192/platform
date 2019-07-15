package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

public interface MyUserVoteForService {
	int countAll();
	List<UserVoteEmpResultEntity> getAll(int pageNumber, int pageSize);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String username, String clientId, String empName, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(String username, String clientId, String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(String username, String clientId, String empName , int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empName(String username, String clientId, String empName);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(String username, String clientId, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCode(String username, String clientId , String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String username, String empName, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCode(String username , String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( String clientId, String empName, String empCode, int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode( String clientId, String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_empNameOrderByRecordTime(String username, String empName, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_empName(String username, String empName);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameAndUserEntity_empCodeOrderByRecordTime(String username,  String empCode, int pageNumber, int pageSize);
	int countByUserEntity_usernameAndUserEntity_empCode(String username, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(String clientId, String empName,  int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndUserEntity_empName(String clientId, String empName);
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(String clientId , String empCode, int pageNumber, int pageSize);
	int countByUserEntity_clientIdAndUserEntity_empCode( String clientId, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String empName , String empCode, int pageNumber, int pageSize);
	int countByUserEntity_empNameAndUserEntity_empCode( String empName, String empCode);
	
	List<UserVoteEmpResultEntity>findByUserEntity_usernameOrderByRecordTime(String username , int pageNumber, int pageSize);
	int countByUserEntity_username( String username );
	
	List<UserVoteEmpResultEntity>findByUserEntity_clientIdOrderByRecordTime(String clientId , int pageNumber, int pageSize);
	int countByUserEntity_clientId( String clientId );
	
	List<UserVoteEmpResultEntity>findByUserEntity_empNameOrderByRecordTime(String empName , int pageNumber, int pageSize);
	int countByUserEntity_empName( String empName );
	
	List<UserVoteEmpResultEntity>findByUserEntity_empCodeOrderByRecordTime(String empCode , int pageNumber, int pageSize);
	int countByUserEntity_empCode( String empCode );
}
