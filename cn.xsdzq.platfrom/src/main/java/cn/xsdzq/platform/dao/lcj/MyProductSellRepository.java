package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

public interface MyProductSellRepository extends PagingAndSortingRepository<ProductSellEntity, Long> {
	Page<ProductSellEntity> findByOrderById(Pageable pageable);
}
