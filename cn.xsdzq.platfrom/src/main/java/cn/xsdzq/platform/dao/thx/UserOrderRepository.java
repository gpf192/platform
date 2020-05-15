package cn.xsdzq.platform.dao.thx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.thx.UserOrderEntity;

public interface UserOrderRepository extends PagingAndSortingRepository<UserOrderEntity, Long> {
	Page<UserOrderEntity> findByOrderById(Pageable pageable);
}
