package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.CwProductSellRepository;
import cn.xsdzq.platform.dao.lcj.MyProductSellRepository;
import cn.xsdzq.platform.entity.lcj.CwSellViewEntity;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;

@Service(value = "cwProductSellServiceImpl")
@Transactional(readOnly = true)
public class CwProductSellServiceImpl implements CwProductSellService{
	@Autowired
	private CwProductSellRepository myProductSellRepository;
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myProductSellRepository.count();
	}

	@Override
	public List<CwSellViewEntity> getAllProductSell(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByOrderByDealTimeDesc(pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;
	}
	
	@Override
	public List<CwSellViewEntity> findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(String clientId, String productCode, String financeAccount, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(clientId, productCode, financeAccount, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndProductCodeAndFinanceAccount(String clientId, String productCode, String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndProductCodeAndFinanceAccount(clientId, productCode, financeAccount);
	}

	@Override
	public List<CwSellViewEntity> findByClientIdAndProductCodeOrderByDealTimeDesc(String clientId, String productCode,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByClientIdAndProductCodeOrderByDealTimeDesc(clientId, productCode, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndProductCode(String clientId, String productCode) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndProductCode(clientId, productCode);
	}

	@Override
	public List<CwSellViewEntity> findByClientIdAndFinanceAccountOrderByDealTimeDesc(String clientId,
			String financeAccount, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByClientIdAndFinanceAccountOrderByDealTimeDesc(clientId, financeAccount, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndFinanceAccount(String clientId, String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndFinanceAccount(clientId, financeAccount);
	}

	@Override
	public List<CwSellViewEntity> findByProductCodeAndFinanceAccountOrderByDealTimeDesc(String productCode,
			String financeAccount, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByProductCodeAndFinanceAccountOrderByDealTimeDesc(productCode, financeAccount, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByProductCodeAndFinanceAccount(String productCode, String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByProductCodeAndFinanceAccount(productCode, financeAccount);
	}

	@Override
	public List<CwSellViewEntity> findByClientIdOrderByDealTimeDesc(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByClientIdOrderByDealTimeDesc(clientId, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientId(clientId);
	}

	@Override
	public List<CwSellViewEntity> findByProductCodeOrderByDealTimeDesc(String productCode, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByProductCodeOrderByDealTimeDesc(productCode, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;	
		}

	@Override
	public int countByProductCode(String productCode) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByProductCode(productCode);
	}

	@Override
	public List<CwSellViewEntity> findByFinanceAccountOrderByDealTimeDesc(String financeAccount, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CwSellViewEntity> pages = myProductSellRepository.findByFinanceAccountOrderByDealTimeDesc(financeAccount, pageRequest);
		List<CwSellViewEntity> infos = pages.getContent();
		return infos;	
	}

	@Override
	public int countByFinanceAccount(String financeAccount) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByFinanceAccount(financeAccount);
	}

}
