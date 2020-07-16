package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.CwSellViewEntity;

public interface CwProductSellService {
	int countAll();
	List<CwSellViewEntity> getAllProductSell(int pageNumber, int pageSize);
		
	List<CwSellViewEntity> findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(String clientId, String productCode, String financeAccount, int pageNumber, int pageSize);
	int countByClientIdAndProductCodeAndFinanceAccount(String clientId, String productCode, String financeAccount);
	
	List<CwSellViewEntity> findByClientIdAndProductCodeOrderByDealTimeDesc(String clientId, String productCode , int pageNumber, int pageSize);
	int countByClientIdAndProductCode(String clientId, String productCode );
	
	List<CwSellViewEntity> findByClientIdAndFinanceAccountOrderByDealTimeDesc(String clientId , String financeAccount, int pageNumber, int pageSize);
	int countByClientIdAndFinanceAccount(String clientId, String financeAccount);
	
	List<CwSellViewEntity> findByProductCodeAndFinanceAccountOrderByDealTimeDesc( String productCode, String financeAccount, int pageNumber, int pageSize);
	int countByProductCodeAndFinanceAccount(  String productCode, String financeAccount);
	
	List<CwSellViewEntity> findByClientIdOrderByDealTimeDesc(String clientId  , int pageNumber, int pageSize);
	int countByClientId(String clientId  );
	
	List<CwSellViewEntity> findByProductCodeOrderByDealTimeDesc(String productCode  , int pageNumber, int pageSize);
	int countByProductCode(String productCode  );
	
	List<CwSellViewEntity> findByFinanceAccountOrderByDealTimeDesc(String financeAccount  , int pageNumber, int pageSize);
	int countByFinanceAccount(String financeAccount  );
}
