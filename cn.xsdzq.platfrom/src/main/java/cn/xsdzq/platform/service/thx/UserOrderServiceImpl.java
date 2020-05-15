package cn.xsdzq.platform.service.thx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.thx.UserOrderRepository;
import cn.xsdzq.platform.entity.thx.UserOrderEntity;

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
	public List<UserOrderEntity> getAllProduct(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserOrderEntity> pages = userOrderRepository.findByOrderById(pageRequest);
		List<UserOrderEntity> infos = pages.getContent();
		return infos;
	}

}
