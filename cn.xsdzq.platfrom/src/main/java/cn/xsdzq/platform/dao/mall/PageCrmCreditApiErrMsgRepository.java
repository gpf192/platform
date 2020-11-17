package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;

public interface PageCrmCreditApiErrMsgRepository extends PagingAndSortingRepository<CRMCreditApiErrorMsgEntity, Long> {
	Page<CRMCreditApiErrorMsgEntity> findByStaOrderByRecordTimeDesc(int sta, Pageable pageable);
	int countBySta(int sta);
	
	Page<CRMCreditApiErrorMsgEntity> findByStaAndSerialNumLikeOrderByRecordTimeDesc(int sta,String serialNum, Pageable pageable);
	int countByStaAndSerialNumLike(int sta,String serialNum);
	
	List<CRMCreditApiErrorMsgEntity> findBySta(int sta);
}
