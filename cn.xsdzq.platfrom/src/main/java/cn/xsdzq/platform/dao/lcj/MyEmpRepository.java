package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpRepository extends PagingAndSortingRepository<EmpEntity, Long> {
	Page<EmpEntity> findByOrderById(Pageable pageable);
}
