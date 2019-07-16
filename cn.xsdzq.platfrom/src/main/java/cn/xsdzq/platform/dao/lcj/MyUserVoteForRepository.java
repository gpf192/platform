package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

public interface MyUserVoteForRepository extends PagingAndSortingRepository<UserVoteEmpResultEntity, Long> {
	Page<UserVoteEmpResultEntity> findByOrderById(Pageable pageable);
	//
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String username, String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String username, String clientId, String empName,  Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empName(String username, String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId , String empCode, Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCode(String username, String clientId , String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username , String empName, String empCode, Pageable pageable);
	int countByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCode(String username , String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(  String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId,  Pageable pageable);
	int countByUserEntity_usernameAndUserEntity_clientId(String username, String clientId);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empNameOrderByRecordTime(String username,  String empName, Pageable pageable);
	int countByUserEntity_usernameAndEmpEntity_empName(String username,  String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameAndEmpEntity_empCodeOrderByRecordTime(String username, String empCode, Pageable pageable);
	int countByUserEntity_usernameAndEmpEntity_empCode(String username, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime( String clientId, String empName, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empName( String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime( String clientId , String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empCode( String clientId, String empCode);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String empName, String empCode, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_empCode( String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_usernameOrderByRecordTime(String username, Pageable pageable);
	int countByUserEntity_username(String username);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdOrderByRecordTime(String clientId, Pageable pageable);
	int countByUserEntity_clientId(String clientId);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empNameOrderByRecordTime(String empName, Pageable pageable);
	int countByEmpEntity_empName(String empName);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empCodeOrderByRecordTime(String empCode, Pageable pageable);
	int countByEmpEntity_empCode(String empCode);
}
