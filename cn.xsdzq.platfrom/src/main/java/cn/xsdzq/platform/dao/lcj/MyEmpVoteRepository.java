package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;

public interface MyEmpVoteRepository extends PagingAndSortingRepository<EmpVoteEntity, Long> {
	Page<EmpVoteEntity> findByOrderById(Pageable pageable);
}
