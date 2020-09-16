package cn.xsdzq.platform.dao.mall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;

public interface PageMallUserInfoRepository extends PagingAndSortingRepository<MallUserInfoEntity, Long>{
	Page<MallUserInfoEntity> findByOrderByCreditScoreDesc(Pageable pageable);
	
	Page<MallUserInfoEntity> findByMallUserEntity_ClientIdOrderByCreditScoreDesc(String clientId, Pageable pageable);
	int countByMallUserEntity_ClientId(String clientId);
	
	//CreditUserTotalEntity findByClientId(String clientId)
}
