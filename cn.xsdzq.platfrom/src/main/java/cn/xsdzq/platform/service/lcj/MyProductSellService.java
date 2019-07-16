package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

public interface MyProductSellService {
	int countAll();
	List<ProductSellEntity> getAllProductSell(int pageNumber, int pageSize);
		
	List<ProductSellEntity> findByClientIdAndCodeAndZjAndLcOrderById(String clientId, String code, String zj, String lc, int pageNumber, int pageSize);
	int countByClientIdAndCodeAndZjAndLc(String clientId, String code, String zj, String lc);
	
	List<ProductSellEntity> findByClientIdAndCodeAndZjOrderById(String clientId, String code, String zj, int pageNumber, int pageSize);
	int countByClientIdAndCodeAndZj(String clientId, String code, String zj);
	
	List<ProductSellEntity> findByClientIdAndCodeAndLcOrderById(String clientId, String code, String lc, int pageNumber, int pageSize);
	int countByClientIdAndCodeAndLc(String clientId, String code, String lc);
	
	List<ProductSellEntity> findByClientIdAndZjAndLcOrderById(String clientId, String zj, String lc, int pageNumber, int pageSize);
	int countByClientIdAndZjAndLc(String clientId,  String zj, String lc);
	
	List<ProductSellEntity> findByCodeAndZjAndLcOrderById(String code, String zj, String lc, int pageNumber, int pageSize);
	int countByCodeAndZjAndLc(String code, String zj, String lc);
	
	List<ProductSellEntity> findByClientIdAndCodeOrderById(String clientId, String code, int pageNumber, int pageSize);
	int countByClientIdAndCode(String clientId, String code);
	
	List<ProductSellEntity> findByClientIdAndZjOrderById(String clientId, String zj, int pageNumber, int pageSize);
	int countByClientIdAndZj(String clientId, String zj);
	
	List<ProductSellEntity> findByClientIdAndLcOrderById(String clientId, String lc, int pageNumber, int pageSize);
	int countByClientIdAndLc(String clientId, String lc);
	
	List<ProductSellEntity> findByCodeAndZjOrderById(String code, String zj, int pageNumber, int pageSize);
	int countByCodeAndZj(String code, String zj);
	
	List<ProductSellEntity> findByCodeAndLcOrderById(String code, String lc, int pageNumber, int pageSize);
	int countByCodeAndLc(String code, String lc);
	
	List<ProductSellEntity> findByZjAndLcOrderById(String zj, String lc, int pageNumber, int pageSize);
	int countByZjAndLc(String zj, String lc);
	
	List<ProductSellEntity> findByClientIdOrderById(String clientId,  int pageNumber, int pageSize);
	int countByClientId(String clientId);
	
	List<ProductSellEntity> findByCodeOrderById(String code,  int pageNumber, int pageSize);
	int countByCode(String code);
	
	List<ProductSellEntity> findByZjOrderById( String zj,   int pageNumber, int pageSize);
	int countByZj(String zj);
	
	List<ProductSellEntity> findByLcOrderById( String lc,   int pageNumber, int pageSize);
	int countByLc(String lc);

}
