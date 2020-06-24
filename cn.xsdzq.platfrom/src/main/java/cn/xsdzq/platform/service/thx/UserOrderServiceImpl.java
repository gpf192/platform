package cn.xsdzq.platform.service.thx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.thx.UserOrderRepository;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

@Service(value = "userOrderServiceImpl")
@Transactional(readOnly = true)
public class UserOrderServiceImpl implements UserOrderService{
	@Autowired
	private UserOrderRepository userOrderRepository;
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.count();
	}

	@Override
	public List<ThxOrderEntity> getAllProduct(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByOrderById(pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndOrderIdAndTgNameAndProductNameOrderByOrderId(String username,
			String orderId, String tgName, String productName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndOrderIdAndTgNameAndProductNameOrderByOrderId(username, orderId, tgName, productName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndOrderIdAndTgNameAndProductName(String username, String orderId, String tgName,
			String productName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndOrderIdAndTgNameAndProductName(username, orderId, tgName, productName);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndOrderIdAndTgNameOrderByOrderId(String username, String orderId,
			String tgName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndOrderIdAndTgNameOrderByOrderId(username, orderId, tgName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndOrderIdAndTgName(String username, String orderId, String tgName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndOrderIdAndTgName(username, orderId, tgName);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndOrderIdAndProductNameOrderByOrderId(String username,
			String orderId,  String productName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndOrderIdAndProductNameOrderByOrderId(username, orderId, productName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndOrderIdAndProductName(String username, String orderId,  
			String productName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndOrderIdAndProductName(username, orderId, productName);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndTgNameAndProductNameOrderByOrderId(String username, String tgName,
			String productName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndTgNameAndProductNameOrderByOrderId(username, tgName, productName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndTgNameAndProductName(String username, String tgName, String productName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndTgNameAndProductName(username, tgName, productName);
	}

	@Override
	public List<ThxOrderEntity> findByOrderIdAndTgNameAndProductNameOrderByOrderId(String orderId, String tgName,
			String productName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByOrderIdAndTgNameAndProductNameOrderByOrderId(orderId, tgName, productName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByOrderIdAndTgNameAndProductName(String orderId, String tgName, String productName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByOrderIdAndTgNameAndProductName(orderId, tgName, productName);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndOrderIdOrderByOrderId(String username, String orderId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndOrderIdOrderByOrderId(username, orderId, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndOrderId(String username, String orderId) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndOrderId( username,  orderId);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndTgNameOrderByOrderId(String username, String tgName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndTgNameOrderByOrderId(username, tgName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndTgName(String username, String tgName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndTgName(username, tgName);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameAndProductNameOrderByOrderId(String username, String productName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameAndProductNameOrderByOrderId(username, productName, pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsernameAndProductName(String username, String productName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByUsernameAndProductName(username, productName);
	}

	@Override
	public List<ThxOrderEntity> findByOrderIdAndTgNameOrderByOrderId(String orderId, String tgName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByOrderIdAndTgNameOrderByOrderId(orderId, tgName,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByOrderIdAndTgName(String orderId, String tgName) {
		// TODO Auto-generated method stub
		return (int)userOrderRepository.countByOrderIdAndTgName(orderId, tgName);
	}

	@Override
	public List<ThxOrderEntity> findByOrderIdAndProductNameOrderByOrderId(String orderId, String productName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByOrderIdAndProductNameOrderByOrderId(orderId, productName,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByOrderIdAndProductName(String orderId, String productName) {
		// TODO Auto-generated method stub
		return  (int)userOrderRepository.countByOrderIdAndProductName(orderId, productName);
	}

	@Override
	public List<ThxOrderEntity> findByTgNameAndProductNameOrderByOrderId(String tgName, String productName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByTgNameAndProductNameOrderByOrderId(tgName, productName,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByTgNameAndProductName(String tgName, String productName) {
		// TODO Auto-generated method stub
		return  (int)userOrderRepository.countByTgNameAndProductName(tgName, productName);
	}

	@Override
	public List<ThxOrderEntity> findByUsernameOrderByOrderId(String username, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByUsernameOrderByOrderId(username,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByUsername(String username) {
		// TODO Auto-generated method stub
		return  (int)userOrderRepository.countByUsername(username);
	}

	@Override
	public List<ThxOrderEntity> findByOrderIdOrderByOrderId(String orderId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByOrderIdOrderByOrderId(orderId,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return  (int)userOrderRepository.countByOrderId(orderId);
	}

	@Override
	public List<ThxOrderEntity> findByTgNameOrderByOrderId(String tgName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByTgNameOrderByOrderId(tgName,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByTgName(String tgName) {
		// TODO Auto-generated method stub
		return  (int)userOrderRepository.countByTgName(tgName);
	}

	@Override
	public List<ThxOrderEntity> findByProductNameOrderByOrderId(String productName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ThxOrderEntity> pages = userOrderRepository.findByProductNameOrderByOrderId(productName,  pageRequest);
		List<ThxOrderEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByProductName(String productName) {
		// TODO Auto-generated method stub
		return  (int)userOrderRepository.countByProductName(productName);
	}
	
}
