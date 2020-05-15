package cn.xsdzq.platform.dao.thx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.thx.UserRiskEntity;

public interface UserRiskRepository extends PagingAndSortingRepository<UserRiskEntity, Long> {
	Page<UserRiskEntity> findByOrderById(Pageable pageable);
}
