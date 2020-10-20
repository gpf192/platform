package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;


public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long> {
	List<CreditRecordEntity> findByTypeAndItemCode(boolean type, String itemCode);
	List<CreditRecordEntity> findByType(boolean type);
	
	//跑批job用
	List<CreditRecordEntity> findByEndDateAndType(int endDate, boolean type);
	List<CreditRecordEntity> findByEndDateLessThanEqualAndType(int endDate, boolean type);
	//查询当前用户的总积分用
	List<CreditRecordEntity> findByClientIdAndType(String clientId, boolean type);
}
