package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyEmpVoteService {
	int countAll();
	List<EmpVoteEntity> getAll(int pageNumber, int pageSize);
	
	List<EmpVoteEntity>findByEmpNameAndVoteFromUserOrderByAccount(String empName, String voteFromUser, int pageNumber, int pageSize);
	int countByEmpNameAndVoteFromUser(String empName, String voteFromUser);
	
	List<EmpVoteEntity>findByEmpNameOrderByAccount(String empName , int pageNumber, int pageSize);
	int countByEmpName(String empName);
	
	List<EmpVoteEntity>findByVoteFromUserOrderByAccount(String voteFromUser, int pageNumber, int pageSize);
	int countByVoteFromUser(String voteFromUser);
}
