package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.AwardResultRepository;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultViewEntity;

@Service(value = "awardResultServiceImpl")
@Transactional(readOnly = true)
public class AwardResultServiceImpl implements AwardResultService{

	@Autowired
	private AwardResultRepository awardResultRepository;
	

	@Override
	public List<AwardResultViewEntity> getAllAwardResult(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)awardResultRepository.count();
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(
			Date beginDate, Date endDate, String prizeName, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(beginDate, endDate, prizeName, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String prizeName, String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(beginDate, endDate, prizeName, ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(beginDate, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqual(beginDate);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualOrderByRecordTimeDesc(endDate, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqual(endDate);
	}
	@Override
	public List<AwardResultViewEntity> findByPrizeCodeOrderByRecordTime(String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByPrizeCodeOrderByRecordTimeDesc(prizeName, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeCode(String prizeName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByPrizeCode(prizeName);
	}
	@Override
	public List<AwardResultViewEntity> findByClientIdOrderByRecordTime(String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByClientIdOrderByRecordTimeDesc(ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientId(String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByClientId(ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(
			Date beginDate, Date endDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(beginDate, endDate, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate,
			String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTimeDesc(beginDate, prizeName, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String prizeName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndPrizeCode(beginDate, prizeName);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(Date beginDate,
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTimeDesc(beginDate, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndClientId(beginDate, ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(Date endDate,
			String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(endDate, prizeName, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeCode(Date endDate, String prizeName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqualAndPrizeCode(endDate, prizeName);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date endDate,
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(endDate, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqualAndClientId(endDate, ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTime(String prizeName, String ClientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByPrizeCodeAndClientIdOrderByRecordTimeDesc(prizeName, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeCodeAndClientId(String prizeName, String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByPrizeCodeAndClientId(prizeName, ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(
			Date beginDate, Date endDate, String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(beginDate,endDate, prizeName, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate,
			String prizeName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(beginDate,endDate, prizeName);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(
			Date beginDate, String prizeName, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(beginDate, prizeName, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate, String prizeName,
			String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(beginDate, prizeName, ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date endDate,
			String prizeName, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(endDate, prizeName, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date endDate, String prizeName, String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(endDate, prizeName, ClientId);
	}
	@Override
	public List<AwardResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(
			Date beginDate, Date endDate, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultViewEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(beginDate,endDate, ClientId, pageable);
		List<AwardResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate,
			Date endDate, String ClientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(beginDate, endDate, ClientId);
	}
	

}
