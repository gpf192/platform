package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

public interface MyProductSellRepository extends PagingAndSortingRepository<ProductSellEntity, Long> {
	Page<ProductSellEntity> findByOrderById(Pageable pageable);
	
	Page<ProductSellEntity> findByClientIdAndCodeAndZjAndLcOrderById(String clientId, String code, String zj, String lc, Pageable pageable);
	int countByClientIdAndCodeAndZjAndLc(String clientId, String code, String zj, String lc);
	
	Page<ProductSellEntity> findByClientIdAndCodeAndZjOrderById(String clientId, String code, String zj,  Pageable pageable);
	int countByClientIdAndCodeAndZj(String clientId, String code, String zj);
	
	Page<ProductSellEntity> findByClientIdAndCodeAndLcOrderById(String clientId, String code, String lc,  Pageable pageable);
	int countByClientIdAndCodeAndLc(String clientId, String code, String lc);
	
	Page<ProductSellEntity> findByClientIdAndZjAndLcOrderById(String clientId,  String zj, String lc, Pageable pageable);
	int countByClientIdAndZjAndLc(String clientId, String zj, String lc);
	
	Page<ProductSellEntity> findByCodeAndZjAndLcOrderById( String code, String zj, String lc, Pageable pageable);
	int countByCodeAndZjAndLc( String code, String zj, String lc);
	
	Page<ProductSellEntity> findByClientIdAndCodeOrderById(String clientId, String code, Pageable pageable);
	int countByClientIdAndCode(String clientId, String code);
	
	Page<ProductSellEntity> findByClientIdAndZjOrderById(String clientId, String zj, Pageable pageable);
	int countByClientIdAndZj(String clientId, String zj);
	
	Page<ProductSellEntity> findByClientIdAndLcOrderById(String clientId, String lc, Pageable pageable);
	int countByClientIdAndLc(String clientId, String lc);
	
	Page<ProductSellEntity> findByCodeAndZjOrderById(String code, String zj,  Pageable pageable);
	int countByCodeAndZj(String code, String zj);
	
	Page<ProductSellEntity> findByCodeAndLcOrderById(String code, String lc,  Pageable pageable);
	int countByCodeAndLc(String code, String lc);
	
	Page<ProductSellEntity> findByZjAndLcOrderById(String zj, String lc, Pageable pageable);
	int countByZjAndLc(String zj, String lc);
	
	Page<ProductSellEntity> findByClientIdOrderById(String clientId , Pageable pageable);
	int countByClientId(String clientId);
	
	Page<ProductSellEntity> findByCodeOrderById(String code,  Pageable pageable);
	int countByCode(String code);
	
	Page<ProductSellEntity> findByZjOrderById( String zj,  Pageable pageable);
	int countByZj(String zj);
	
	Page<ProductSellEntity> findByLcOrderById( String lc,  Pageable pageable);
	int countByLc(String lc);
}
