package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteForEntity;

public interface MyUserVoteForRepository extends PagingAndSortingRepository<UserVoteForEntity, Long> {
	Page<UserVoteForEntity> findByOrderById(Pageable pageable);
	//
	Page<UserVoteForEntity> findByUsernameAndClientIdAndEmpNameAndEmpCodeOrderByVoteTime(String username, String clientId, String empName, String empCode, Pageable pageable);
	int countByUsernameAndClientIdAndEmpNameAndEmpCode(String username, String clientId, String empName, String empCode);
	
	Page<UserVoteForEntity> findByUsernameAndClientIdAndEmpNameOrderByVoteTime(String username, String clientId, String empName,  Pageable pageable);
	int countByUsernameAndClientIdAndEmpName(String username, String clientId, String empName);
	
	Page<UserVoteForEntity> findByUsernameAndClientIdAndEmpCodeOrderByVoteTime(String username, String clientId , String empCode, Pageable pageable);
	int countByUsernameAndClientIdAndEmpCode(String username, String clientId , String empCode);
	
	Page<UserVoteForEntity> findByUsernameAndEmpNameAndEmpCodeOrderByVoteTime(String username , String empName, String empCode, Pageable pageable);
	int countByUsernameAndEmpNameAndEmpCode(String username , String empName, String empCode);
	
	Page<UserVoteForEntity> findByClientIdAndEmpNameAndEmpCodeOrderByVoteTime( String clientId, String empName, String empCode, Pageable pageable);
	int countByClientIdAndEmpNameAndEmpCode(  String clientId, String empName, String empCode);
	
	Page<UserVoteForEntity> findByUsernameAndClientIdOrderByVoteTime(String username, String clientId,  Pageable pageable);
	int countByUsernameAndClientId(String username, String clientId);
	
	Page<UserVoteForEntity> findByUsernameAndEmpNameOrderByVoteTime(String username,  String empName, Pageable pageable);
	int countByUsernameAndEmpName(String username,  String empName);
	
	Page<UserVoteForEntity> findByUsernameAndEmpCodeOrderByVoteTime(String username, String empCode, Pageable pageable);
	int countByUsernameAndEmpCode(String username, String empCode);
	
	Page<UserVoteForEntity> findByClientIdAndEmpNameOrderByVoteTime( String clientId, String empName, Pageable pageable);
	int countByClientIdAndEmpName( String clientId, String empName);
	
	Page<UserVoteForEntity> findByClientIdAndEmpCodeOrderByVoteTime( String clientId , String empCode, Pageable pageable);
	int countByClientIdAndEmpCode( String clientId, String empCode);
	
	Page<UserVoteForEntity> findByEmpNameAndEmpCodeOrderByVoteTime(String empName, String empCode, Pageable pageable);
	int countByEmpNameAndEmpCode( String empName, String empCode);
	
	Page<UserVoteForEntity> findByUsernameOrderByVoteTime(String username, Pageable pageable);
	int countByUsername(String username);
	
	Page<UserVoteForEntity> findByClientIdOrderByVoteTime(String clientId, Pageable pageable);
	int countByClientId(String clientId);
	
	Page<UserVoteForEntity> findByEmpNameOrderByVoteTime(String empName, Pageable pageable);
	int countByEmpName(String empName);
	
	Page<UserVoteForEntity> findByEmpCodeOrderByVoteTime(String empCode, Pageable pageable);
	int countByEmpCode(String empCode);
}
