package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;

public interface PageCrmCreditRecordRepository  extends PagingAndSortingRepository<CRMCreditRecordEntity, Long> {
	Page<CRMCreditRecordEntity> findByOrderByBeginDateDesc(Pageable pageable);
	
	Page<CRMCreditRecordEntity> findByClientIdLikeOrderByBeginDateDesc(String clientId, Pageable pageable);
	int countByClientIdLike(String clientId);
	
	Page<CRMCreditRecordEntity> findBySerialNumLikeOrderByBeginDateDesc(String serialNum, Pageable pageable);
	int countBySerialNumLike(String serialNum);
	
	Page<CRMCreditRecordEntity> findByItemCodeLikeOrderByBeginDateDesc(String itemCode, Pageable pageable);
	int countByItemCodeLike(String itemCode);
	//2个
	
	
	Page<CRMCreditRecordEntity> findByClientIdLikeAndSerialNumLikeOrderByBeginDateDesc(String clientId,String serialNum, Pageable pageable);
	int countByClientIdLikeAndSerialNumLike(String clientId,String serialNum);
	
	Page<CRMCreditRecordEntity> findByClientIdLikeAndItemCodeLikeOrderByBeginDateDesc(String clientId,String itemCode, Pageable pageable);
	int countByClientIdLikeAndItemCodeLike(String clientId,String itemCode);
	
	Page<CRMCreditRecordEntity> findByItemCodeLikeAndSerialNumLikeOrderByBeginDateDesc(String itemCode,String serialNum, Pageable pageable);
	int countByItemCodeLikeAndSerialNumLike(String itemCode,String serialNum);
	//
	Page<CRMCreditRecordEntity> findByClientIdLikeAndSerialNumLikeAndItemCodeLikeOrderByBeginDateDesc(String clientId,String serialNum,String itemCode, Pageable pageable);
	int countByClientIdLikeAndSerialNumLikeAndItemCodeLike(String clientId,String serialNum,String itemCode);
	//
	
	//crm 扫描job
	List<CRMCreditRecordEntity> findByBeginDateGreaterThan(int beginDate);
	CRMCreditRecordEntity findBySerialNum(String serialNum);
	
}
