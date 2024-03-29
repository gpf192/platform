package cn.xsdzq.platform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.xsdzq.platform.dao.MyKCRepository;
import cn.xsdzq.platform.entity.CustomerMobileEntity;

@Service(value = "kcServiceImpl")
@Transactional(readOnly = true)
public class KCServiceImpl implements KCService{
	@Autowired
	private MyKCRepository myKCRepository;
	@Override
	public List<CustomerMobileEntity> getKCInfos(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CustomerMobileEntity> pages = myKCRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<CustomerMobileEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myKCRepository.count();
	}
	@Override
	public List<CustomerMobileEntity> findByEndTimeLessThanEqualAndBeginTimeGreaterThanEqualOrderByRecordTimeDesc(
			Date endDate, Date beginDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CustomerMobileEntity> pages = myKCRepository.findByRecordTimeLessThanEqualAndRecordTimeGreaterThanEqualOrderByRecordTimeDesc(endDate, beginDate, pageRequest);//问题 ：无法排序
		List<CustomerMobileEntity> plans = pages.getContent();
		return plans;
	}
	@Override
	public int countByEndTimeLessThanEqualAndBeginTimeGreaterThanEqual(Date endDate, Date beginDate) {
		// TODO Auto-generated method stub
		return (int) myKCRepository.countByRecordTimeLessThanEqualAndRecordTimeGreaterThanEqual(endDate, beginDate);
	}
	
	@Override
	public List<CustomerMobileEntity> findByBeginTimeGreaterThanEqualOrderByRecordTimeDesc(Date beginDate,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CustomerMobileEntity> pages = myKCRepository.findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(beginDate, pageRequest);//问题 ：无法排序
		List<CustomerMobileEntity> plans = pages.getContent();
		return plans;
	}
	@Override
	public int countByBeginTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return (int) myKCRepository.countByRecordTimeGreaterThanEqual(beginDate);
	}
	
	@Override
	public List<CustomerMobileEntity> findByEndTimeLessThanEqualOrderByRecordTimeDesc(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CustomerMobileEntity> pages = myKCRepository.findByRecordTimeLessThanEqualOrderByRecordTimeDesc(endDate, pageRequest);//问题 ：无法排序
		List<CustomerMobileEntity> plans = pages.getContent();
		return plans;
	}
	@Override
	public int countByEndTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return (int) myKCRepository.countByRecordTimeLessThanEqual(endDate);
	}
	
	

}
