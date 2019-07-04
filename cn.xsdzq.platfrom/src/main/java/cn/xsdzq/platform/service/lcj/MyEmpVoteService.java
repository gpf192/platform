package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyEmpVoteService {
	int countAll();
	List<EmpVoteEntity> getAll(int pageNumber, int pageSize);
	
	List<EmpVoteEntity>findByEmpNameAndVoteFromUserOrderByWeightDesc(String empName, String voteFromUser, int pageNumber, int pageSize);
	int countByEmpNameAndVoteFromUser(String empName, String voteFromUser);
	
	List<EmpVoteEntity>findByEmpNameOrderByWeightDesc(String empName , int pageNumber, int pageSize);
	int countByEmpName(String empName);
	
	List<EmpVoteEntity>findByVoteFromUserOrderByWeightDesc(String voteFromUser, int pageNumber, int pageSize);
	int countByVoteFromUser(String voteFromUser);
}
