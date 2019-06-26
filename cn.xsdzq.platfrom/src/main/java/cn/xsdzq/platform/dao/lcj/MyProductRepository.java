package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.ProductEntity;

public interface MyProductRepository  extends PagingAndSortingRepository<ProductEntity, Long> {
	Page<ProductEntity> findByOrderById(Pageable pageable);
}
