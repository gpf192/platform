package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

public interface MyProductSellRepository extends PagingAndSortingRepository<ProductSellEntity, Long> {
	Page<ProductSellEntity> findByOrderByDealTimeDesc(Pageable pageable); 
	
	Page<ProductSellEntity> findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(String clientId, String productCode, String financeAccount, Pageable pageable);
	int countByClientIdAndProductCodeAndFinanceAccount(String clientId, String productCode, String financeAccount);
	
	Page<ProductSellEntity> findByClientIdAndProductCodeOrderByDealTimeDesc(String clientId, String productCode,  Pageable pageable);
	int countByClientIdAndProductCode(String clientId, String productCode );
	
	Page<ProductSellEntity> findByClientIdAndFinanceAccountOrderByDealTimeDesc(String clientId , String financeAccount, Pageable pageable);
	int countByClientIdAndFinanceAccount(String clientId , String financeAccount);
	
	Page<ProductSellEntity> findByProductCodeAndFinanceAccountOrderByDealTimeDesc( String productCode, String financeAccount, Pageable pageable);
	int countByProductCodeAndFinanceAccount( String productCode, String financeAccount);
	
	Page<ProductSellEntity> findByClientIdOrderByDealTimeDesc(String clientId ,  Pageable pageable);
	int countByClientId(String clientId  );
	
	Page<ProductSellEntity> findByProductCodeOrderByDealTimeDesc( String productCode,  Pageable pageable);
	int countByProductCode(  String productCode );
	
	Page<ProductSellEntity> findByFinanceAccountOrderByDealTimeDesc( String financeAccount, Pageable pageable);
	int countByFinanceAccount( String financeAccount);
}
