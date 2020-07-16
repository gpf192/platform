package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeEntity;

public interface MyPrizeRepository extends PagingAndSortingRepository<PrizeEntity, Long> {
	Page<PrizeEntity> findByOrderById(Pageable pageable);
}
