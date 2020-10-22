package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;

public interface PageCrmCreditApiErrMsgRepository extends PagingAndSortingRepository<CRMCreditApiErrorMsgEntity, Long> {
	Page<CRMCreditApiErrorMsgEntity> findByOrderByRecordTimeDesc(Pageable pageable);
	
	Page<CRMCreditApiErrorMsgEntity> findBySerialNumLikeOrderByRecordTimeDesc(String serialNum, Pageable pageable);
	int countBySerialNumLike(String serialNum);
}
