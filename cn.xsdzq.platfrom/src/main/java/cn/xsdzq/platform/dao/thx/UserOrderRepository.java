package cn.xsdzq.platform.dao.thx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

public interface UserOrderRepository extends PagingAndSortingRepository<ThxOrderEntity, Long> {
	Page<ThxOrderEntity> findByOrderById(Pageable pageable);
}
