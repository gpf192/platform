package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

public interface MyProductSellService {
	int countAll();
	List<ProductSellEntity> getAllProductSell(int pageNumber, int pageSize);
		
	List<ProductSellEntity> findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(String clientId, String productCode, String financeAccount, int pageNumber, int pageSize);
	int countByClientIdAndProductCodeAndFinanceAccount(String clientId, String productCode, String financeAccount);
	
	List<ProductSellEntity> findByClientIdAndProductCodeOrderByDealTimeDesc(String clientId, String productCode , int pageNumber, int pageSize);
	int countByClientIdAndProductCode(String clientId, String productCode );
	
	List<ProductSellEntity> findByClientIdAndFinanceAccountOrderByDealTimeDesc(String clientId , String financeAccount, int pageNumber, int pageSize);
	int countByClientIdAndFinanceAccount(String clientId, String financeAccount);
	
	List<ProductSellEntity> findByProductCodeAndFinanceAccountOrderByDealTimeDesc( String productCode, String financeAccount, int pageNumber, int pageSize);
	int countByProductCodeAndFinanceAccount(  String productCode, String financeAccount);
	
	List<ProductSellEntity> findByClientIdOrderByDealTimeDesc(String clientId  , int pageNumber, int pageSize);
	int countByClientId(String clientId  );
	
	List<ProductSellEntity> findByProductCodeOrderByDealTimeDesc(String productCode  , int pageNumber, int pageSize);
	int countByProductCode(String productCode  );
	
	List<ProductSellEntity> findByFinanceAccountOrderByDealTimeDesc(String financeAccount  , int pageNumber, int pageSize);
	int countByFinanceAccount(String financeAccount  );
}
