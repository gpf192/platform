package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;

public interface MyPrizeRecordRepository extends PagingAndSortingRepository<PrizeRecordEntity, Long> {
	Page<PrizeRecordEntity> findByOrderById(Pageable pageable);

}
