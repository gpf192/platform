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
		Page<ProductSellEntity> pages = myProductSellRepository.findByOrderById(pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}
	
	@Override
	public List<ProductSellEntity> findByClientIdAndCodeAndZjAndLcOrderById(String clientId, String code, String zj, String lc,  int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndCodeAndZjAndLcOrderById(clientId, code, zj, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndCodeAndZjAndLc(String clientId, String code, String zj, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndCodeAndZjAndLc(clientId, code, zj, lc);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndCodeAndZjOrderById(String clientId, String code, String zj,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndCodeAndZjOrderById(clientId, code, zj, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndCodeAndZj(String clientId, String code, String zj) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndCodeAndZj(clientId, code, zj);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndCodeAndLcOrderById(String clientId, String code, String lc,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndCodeAndLcOrderById(clientId, code, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;	
		}

	@Override
	public int countByClientIdAndCodeAndLc(String clientId, String code, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndCodeAndLc(clientId, code, lc);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndZjAndLcOrderById(String clientId, String zj, String lc,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndZjAndLcOrderById(clientId, zj, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
		
	}

	@Override
	public int countByClientIdAndZjAndLc(String clientId, String zj, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndZjAndLc(clientId, zj, lc);
	}

	@Override
	public List<ProductSellEntity> findByCodeAndZjAndLcOrderById(String code, String zj, String lc, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByCodeAndZjAndLcOrderById(code, zj, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCodeAndZjAndLc(String code, String zj, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByCodeAndZjAndLc(code, zj, lc);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndCodeOrderById(String clientId, String code, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndCodeOrderById(clientId, code, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndCode(String clientId, String code) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndCode(clientId, code);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndZjOrderById(String clientId, String zj, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndZjOrderById(clientId, zj, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndZj(String clientId, String zj) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndZj(clientId, zj);
	}

	@Override
	public List<ProductSellEntity> findByClientIdAndLcOrderById(String clientId, String lc, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdAndLcOrderById(clientId, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdAndLc(String clientId, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientIdAndZj(clientId, lc);
	}

	@Override
	public List<ProductSellEntity> findByCodeAndZjOrderById(String code, String zj, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByCodeAndZjOrderById(code, zj, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCodeAndZj(String code, String zj) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByCodeAndZj(code, zj);
	}

	@Override
	public List<ProductSellEntity> findByCodeAndLcOrderById(String code, String lc, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByCodeAndLcOrderById(code, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCodeAndLc(String code, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByCodeAndLc(code, lc);
	}

	@Override
	public List<ProductSellEntity> findByZjAndLcOrderById(String zj, String lc, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByZjAndLcOrderById(zj, lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByZjAndLc(String zj, String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByZjAndLc(zj, lc);
	}

	@Override
	public List<ProductSellEntity> findByClientIdOrderById(String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByClientIdOrderById(clientId, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByClientId(clientId);
	}

	@Override
	public List<ProductSellEntity> findByCodeOrderById(String code, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByCodeOrderById(code, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCode(String code) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByCode(code);
	}

	@Override
	public List<ProductSellEntity> findByZjOrderById(String zj, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByZjOrderById(zj, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;	
		}

	@Override
	public int countByZj(String zj) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByZj(zj);
	}

	@Override
	public List<ProductSellEntity> findByLcOrderById(String lc, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ProductSellEntity> pages = myProductSellRepository.findByLcOrderById(lc, pageRequest);
		List<ProductSellEntity> infos = pages.getContent();
		return infos;	
	}

	@Override
	public int countByLc(String lc) {
		// TODO Auto-generated method stub
		return myProductSellRepository.countByLc(lc);
	}
}
