package cn.xsdzq.platform.service.thx;

import java.util.Date;
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
		Page<UserRiskEntity> pages = userRiskRepository.findByOrderByEvaluationTimeDesc(pageRequest);
		List<UserRiskEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<UserRiskEntity> findByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(
			Date endDate, Date beginDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserRiskEntity> pages = userRiskRepository.findByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(endDate, beginDate, pageRequest);;
		List<UserRiskEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqual(Date endDate, Date beginDate) {
		// TODO Auto-generated method stub
		return (int) userRiskRepository.countByEvaluationTimeLessThanEqualAndEvaluationTimeGreaterThanEqual(endDate, beginDate);
	}

	@Override
	public List<UserRiskEntity> findByEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(Date beginDate,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserRiskEntity> pages = userRiskRepository.findByEvaluationTimeGreaterThanEqualOrderByEvaluationTimeDesc(beginDate, pageRequest);
		List<UserRiskEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEvaluationTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return (int) userRiskRepository.countByEvaluationTimeGreaterThanEqual(beginDate);
	}

	@Override
	public List<UserRiskEntity> findByEvaluationTimeLessThanEqualOrderByEvaluationTimeDesc(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserRiskEntity> pages = userRiskRepository.findByEvaluationTimeLessThanEqualOrderByEvaluationTimeDesc(endDate, pageRequest);;
		List<UserRiskEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByEvaluationTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return (int) userRiskRepository.countByEvaluationTimeLessThanEqual(endDate);
	}

}
