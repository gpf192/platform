package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

public interface MyUserVoteForRepository extends PagingAndSortingRepository<UserVoteEmpResultEntity, Long> {
	Page<UserVoteEmpResultEntity> findByOrderById(Pageable pageable);
	//
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(String username, String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(String username, String clientId, String empName,  Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientIdAndEmpEntity_empName(String username, String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(String username, String clientId , String empCode, Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientIdAndEmpEntity_empCode(String username, String clientId , String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String username , String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientNameAndEmpEntity_empNameAndEmpEntity_empCode(String username , String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(  String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndUserEntity_clientIdOrderByRecordTime(String username, String clientId,  Pageable pageable);
	int countByUserEntity_clientNameAndUserEntity_clientId(String username, String clientId);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndEmpEntity_empNameOrderByRecordTime(String username,  String empName, Pageable pageable);
	int countByUserEntity_clientNameAndEmpEntity_empName(String username,  String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameAndEmpEntity_empCodeOrderByRecordTime(String username, String empCode, Pageable pageable);
	int countByUserEntity_clientNameAndEmpEntity_empCode(String username, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime( String clientId, String empName, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empName( String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime( String clientId , String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empCode( String clientId, String empCode);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(String empName, String empCode, Pageable pageable);
	int countByEmpEntity_empNameAndEmpEntity_empCode( String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameOrderByRecordTime(String username, Pageable pageable);
	int countByUserEntity_clientName(String username);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdOrderByRecordTime(String clientId, Pageable pageable);
	int countByUserEntity_clientId(String clientId);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empNameOrderByRecordTime(String empName, Pageable pageable);
	int countByEmpEntity_empName(String empName);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empCodeOrderByRecordTime(String empCode, Pageable pageable);
	int countByEmpEntity_empCode(String empCode);
}
