package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.UserVoteEntity;

public interface MyUserVoteRepository extends PagingAndSortingRepository<UserVoteEntity, Long> {
	Page<UserVoteEntity> findByOrderById(Pageable pageable);
}
