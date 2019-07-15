package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

public interface MyUserVoteForRepository extends PagingAndSortingRepository<UserVoteEmpResultEntity, Long> {
	Page<UserVoteEmpResultEntity> findByOrderById(Pageable pageable);
	//
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String username, String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(String username, String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(String username, String clientId, String empName,  Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empName(String username, String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(String username, String clientId , String empCode, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCode(String username, String clientId , String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String username , String empName, String empCode, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCode(String username , String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(  String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId,  Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_empNameOrderByRecordTime(String username,  String empName, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_empName(String username,  String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_empCodeOrderByRecordTime(String username, String empCode, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_empCode(String username, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime( String clientId, String empName, Pageable pageable);
	int countByUserEntity_clientIdAndUserEntity_empName( String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime( String clientId , String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndUserEntity_empCode( String clientId, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(String empName, String empCode, Pageable pageable);
	int countByUserEntity_empNameAndUserEntity_empCode( String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameOrderByRecordTime(String username, Pageable pageable);
	int countByUserEntity_username(String username);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdOrderByRecordTime(String clientId, Pageable pageable);
	int countByUserEntity_clientId(String clientId);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_empNameOrderByRecordTime(String empName, Pageable pageable);
	int countByUserEntity_empName(String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_empCodeOrderByRecordTime(String empCode, Pageable pageable);
	int countByUserEntity_empCode(String empCode);
}
