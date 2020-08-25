package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;

public interface MyUserVoteForRepository extends PagingAndSortingRepository<UserVoteEmpResultEntity, Long> {
	Page<UserVoteEmpResultEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	//
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc(String username, String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCode(String username, String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLikeOrderByRecordTimeDesc(String username, String clientId, String empName,  Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empNameLike(String username, String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTimeDesc(String username, String clientId , String empCode, Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientIdAndEmpEntity_empCode(String username, String clientId , String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc(String username , String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientNameLikeAndEmpEntity_empNameLikeAndEmpEntity_empCode(String username , String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc( String clientId, String empName, String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empNameLikeAndEmpEntity_empCode(  String clientId, String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdOrderByRecordTimeDesc(String username, String clientId,  Pageable pageable);
	int countByUserEntity_clientNameLikeAndUserEntity_clientId(String username, String clientId);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndEmpEntity_empNameLikeOrderByRecordTimeDesc(String username,  String empName, Pageable pageable);
	int countByUserEntity_clientNameLikeAndEmpEntity_empNameLike(String username,  String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc(String username, String empCode, Pageable pageable);
	int countByUserEntity_clientNameLikeAndEmpEntity_empCode(String username, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empNameLikeOrderByRecordTimeDesc( String clientId, String empName, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empNameLike( String clientId, String empName);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTimeDesc( String clientId , String empCode, Pageable pageable);
	int countByUserEntity_clientIdAndEmpEntity_empCode( String clientId, String empCode);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByRecordTimeDesc(String empName, String empCode, Pageable pageable);
	int countByEmpEntity_empNameLikeAndEmpEntity_empCode( String empName, String empCode);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientNameLikeOrderByRecordTimeDesc(String username, Pageable pageable);
	int countByUserEntity_clientNameLike(String username);
	
	Page<UserVoteEmpResultEntity> findByUserEntity_clientIdOrderByRecordTimeDesc(String clientId, Pageable pageable);
	int countByUserEntity_clientId(String clientId);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empNameLikeOrderByRecordTimeDesc(String empName, Pageable pageable);
	int countByEmpEntity_empNameLike(String empName);
	
	Page<UserVoteEmpResultEntity> findByEmpEntity_empCodeOrderByRecordTimeDesc(String empCode, Pageable pageable);
	int countByEmpEntity_empCode(String empCode);
}
