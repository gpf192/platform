package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;


public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long> {
	List<CreditRecordEntity> findByTypeAndItemCode(boolean type, String itemCode);
	List<CreditRecordEntity> findByType(boolean type);
	
	//跑批job用
	List<CreditRecordEntity> findByEndDateAndTypeAndChangeTypeIn(int endDate, boolean type,List<Integer> list);
	List<CreditRecordEntity> findByBeginDateAndTypeAndChangeTypeIn(String preDay, boolean type,List<Integer> list);
	List<CreditRecordEntity> findByClientIdAndEndDateLessThanEqualAndType(String clientId, int endDate, boolean type);
	//查询当前用户的总积分用
	List<CreditRecordEntity> findByClientIdAndType(String clientId, boolean type);
}
