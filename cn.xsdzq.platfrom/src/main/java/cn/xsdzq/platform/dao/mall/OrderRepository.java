package cn.xsdzq.platform.dao.mall;

import cn.xsdzq.platform.entity.mall.MallOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface OrderRepository extends JpaRepository<MallOrderEntity, Long> {

    Page<MallOrderEntity> findByCreateTimeGreaterThanAndCreateTimeLessThanAndOrderStatusInOrderByIdAsc(Date startTime, Date endTime, Collection<Integer> orderStatus, Pageable pageable);

}