package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;

public interface MyEmpVoteService {
	int countAll();
	List<EmpVoteEntity> getAll(int pageNumber, int pageSize);
}
