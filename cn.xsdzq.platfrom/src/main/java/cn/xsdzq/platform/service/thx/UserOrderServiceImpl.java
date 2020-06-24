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
	
}
