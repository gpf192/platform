package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;


public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long> {
	List<CreditRecordEntity> findByTypeAndItemCode(boolean type, String itemCode);
	List<CreditRecordEntity> findByType(boolean type);
}
