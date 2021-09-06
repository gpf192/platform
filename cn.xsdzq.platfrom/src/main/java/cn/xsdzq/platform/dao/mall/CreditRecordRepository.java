package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;


public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long> {
	List<CreditRecordEntity> findByTypeAndItemCode(boolean type, String itemCode);
	List<CreditRecordEntity> findByItemCode( String itemCode);
	List<CreditRecordEntity> findByType(boolean type);
	
	//跑批job用
	List<CreditRecordEntity> findByEndDateAndTypeAndChangeTypeIn(int endDate, boolean type,List<Integer> list);
	List<CreditRecordEntity> findByBeginDateAndTypeAndChangeTypeIn(String preDay, boolean type,List<Integer> list);
	List<CreditRecordEntity> findByClientIdAndEndDateLessThanEqualAndType(String clientId, int endDate, boolean type);
	List<CreditRecordEntity> findBySerialNum(String serialNum);
	//查询当前用户的总积分用
	List<CreditRecordEntity> findByClientIdAndType(String clientId, boolean type);
	//邮件自动推送查询
	List<CreditRecordEntity> findByTypeAndItemCodeAndDateFlag(boolean type, String itemCode, String date);
	
	@Query(value = "select r from CreditRecordEntity r where r.mallUserEntity = ?1 and r.type = 1 and r.changeType <=1 order by r.endDate , r.recordTime")
	List<CreditRecordEntity> findByUnusedCredit(MallUserEntity mallUserEntity, Boolean type, int changeType);

}
