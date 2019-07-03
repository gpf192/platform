package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyEmpVoteRepository extends PagingAndSortingRepository<EmpVoteEntity, Long> {
	Page<EmpVoteEntity> findByOrderById(Pageable pageable);
	
	Page<EmpVoteEntity> findByEmpNameAndVoteFromUserOrderByAccount(String empName, String voteFromUser, Pageable pageable);
	int countByEmpNameAndVoteFromUser(String empName, String voteFromUser);
	
	Page<EmpVoteEntity> findByEmpNameOrderByAccount(String empName , Pageable pageable);
	int countByEmpName(String empName );
	
	Page<EmpVoteEntity> findByVoteFromUserOrderByAccount(String voteFromUser, Pageable pageable);
	int countByVoteFromUser(String voteFromUser);
}
