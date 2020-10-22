package cn.xsdzq.platform.service.mall;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;

public interface CRMCreditRecordService {
	List<CRMCreditRecordEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize);
	 int countAll();
	List<CRMCreditRecordEntity> findByClientIdLikeOrderByBeginDateDesc(String clientId,int pageNumber, int pageSize);
	int countByClientIdLike(String clientId);
	
	List<CRMCreditRecordEntity> findBySerialNumLikeOrderByBeginDateDesc(String serialNum, int pageNumber, int pageSize);
	int countBySerialNumLike(String serialNum);
	
	List<CRMCreditRecordEntity> findByItemCodeLikeOrderByBeginDateDesc(String itemCode, int pageNumber, int pageSize);
	int countByItemCodeLike(String itemCode);
	
	List<CRMCreditRecordEntity> findByClientIdLikeAndSerialNumLikeOrderByBeginDateDesc(String clientId,String serialNum, int pageNumber, int pageSize);
	int countByClientIdLikeAndSerialNumLike(String clientId,String serialNum);
	
	List<CRMCreditRecordEntity> findByClientIdLikeAndItemCodeLikeOrderByBeginDateDesc(String clientId,String itemCode, int pageNumber, int pageSize);
	int countByClientIdLikeAndItemCodeLike(String clientId,String itemCode);
	
	List<CRMCreditRecordEntity> findByItemCodeLikeAndSerialNumLikeOrderByBeginDateDesc(String itemCode,String serialNum, int pageNumber, int pageSize);
	int countByItemCodeLikeAndSerialNumLike(String itemCode,String serialNum);
	
	List<CRMCreditRecordEntity> findByClientIdLikeAndSerialNumLikeAndItemCodeLikeOrderByBeginDateDesc(String clientId,String serialNum,String itemCode, int pageNumber, int pageSize);
	int countByClientIdLikeAndSerialNumLikeAndItemCodeLike(String clientId,String serialNum,String itemCode);
	
	//
	
	List<CRMCreditApiErrorMsgEntity> findMsgByOrderByRecordTimeDesc(int pageNumber, int pageSize);
	 int countMsgAll();
	List<CRMCreditApiErrorMsgEntity> findMsgBySerialNumLikeOrderByRecordTimeDesc(String serialNum,int pageNumber, int pageSize);
	int countMsgBySerialNumLike(String serialNum);
}
