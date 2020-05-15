package cn.xsdzq.platform.service.thx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.thx.UserRiskRepository;
import cn.xsdzq.platform.entity.thx.UserRiskEntity;

@Service(value = "userRiskServiceImpl")
@Transactional(readOnly = true)
public class UserRiskServiceImpl implements UserRiskService{
	@Autowired
	private UserRiskRepository userRiskRepository;
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)userRiskRepository.count();
	}

	@Override
	public List<UserRiskEntity> getAllUserRisk(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserRiskEntity> pages = userRiskRepository.findByOrderById(pageRequest);
		List<UserRiskEntity> infos = pages.getContent();
		return infos;
	}

}
