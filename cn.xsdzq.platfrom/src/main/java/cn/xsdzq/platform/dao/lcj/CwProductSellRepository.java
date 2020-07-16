package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.CwSellViewEntity;

public interface CwProductSellRepository extends PagingAndSortingRepository<CwSellViewEntity, Long> {
	Page<CwSellViewEntity> findByOrderByDealTimeDesc(Pageable pageable); 
	
	Page<CwSellViewEntity> findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(String clientId, String productCode, String financeAccount, Pageable pageable);
	int countByClientIdAndProductCodeAndFinanceAccount(String clientId, String productCode, String financeAccount);
	
	Page<CwSellViewEntity> findByClientIdAndProductCodeOrderByDealTimeDesc(String clientId, String productCode,  Pageable pageable);
	int countByClientIdAndProductCode(String clientId, String productCode );
	
	Page<CwSellViewEntity> findByClientIdAndFinanceAccountOrderByDealTimeDesc(String clientId , String financeAccount, Pageable pageable);
	int countByClientIdAndFinanceAccount(String clientId , String financeAccount);
	
	Page<CwSellViewEntity> findByProductCodeAndFinanceAccountOrderByDealTimeDesc( String productCode, String financeAccount, Pageable pageable);
	int countByProductCodeAndFinanceAccount( String productCode, String financeAccount);
	
	Page<CwSellViewEntity> findByClientIdOrderByDealTimeDesc(String clientId ,  Pageable pageable);
	int countByClientId(String clientId  );
	
	Page<CwSellViewEntity> findByProductCodeOrderByDealTimeDesc( String productCode,  Pageable pageable);
	int countByProductCode(  String productCode );
	
	Page<CwSellViewEntity> findByFinanceAccountOrderByDealTimeDesc( String financeAccount, Pageable pageable);
	int countByFinanceAccount( String financeAccount);
}
