package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyProductSellRepository;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

@Service(value = "myProductSellServiceImpl")
@Transactional(readOnly = true)
public class MyProductSellServiceImpl implements MyProductSellService{
	@Autowired
	private MyProductSellRepository myProductSellRepository;
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myProductSellRepository.count();
	}

	@Override
	public List<ProductSellEntity> getAllProductSell(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByOrderByDealTimeDesc(pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}
	
	@Override
	public List<ProductSellEntity> findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(String clientId, String productCode, String financeAccount, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(clientId, productCode, financeAccount, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndProductCodeAndFinanceAccount(String clientId, String productCode, String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndProductCodeAndFinanceAccount(clientId, productCode, financeAccount);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndProductCodeOrderByDealTimeDesc(String clientId, String productCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndProductCodeOrderByDealTimeDesc(clientId, productCode, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndProductCode(String clientId, String productCode) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndProductCode(clientId, productCode);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndFinanceAccountOrderByDealTimeDesc(String clientId,
			String financeAccount, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndFinanceAccountOrderByDealTimeDesc(clientId, financeAccount, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndFinanceAccount(String clientId, String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndFinanceAccount(clientId, financeAccount);
	}

	@Override
	public List<ProductSellEntity> findByProductCodeAndFinanceAccountOrderByDealTimeDesc(String productCode,
			String financeAccount, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByProductCodeAndFinanceAccountOrderByDealTimeDesc(productCode, financeAccount, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByProductCodeAndFinanceAccount(String productCode, String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByProductCodeAndFinanceAccount(productCode, financeAccount);
	}

	@Override
	public List<ProductSellEntity> findByClientIdOrderByDealTimeDesc(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdOrderByDealTimeDesc(clientId, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientId(clientId);
	}

	@Override
	public List<ProductSellEntity> findByProductCodeOrderByDealTimeDesc(String productCode, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByProductCodeOrderByDealTimeDesc(productCode, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;	
		}

	@Override
	public int countByProductCode(String productCode) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByProductCode(productCode);
	}

	@Override
	public List<ProductSellEntity> findByFinanceAccountOrderByDealTimeDesc(String financeAccount, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByFinanceAccountOrderByDealTimeDesc(financeAccount, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;	
	}

	@Override
	public int countByFinanceAccount(String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByFinanceAccount(financeAccount);
	}

}
