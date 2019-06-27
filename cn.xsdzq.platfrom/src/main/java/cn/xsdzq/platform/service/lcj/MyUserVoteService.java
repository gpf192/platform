package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyUserVoteService {
	int countAll();
	List<UserVoteEntity> getAll(int pageNumber, int pageSize);
}
