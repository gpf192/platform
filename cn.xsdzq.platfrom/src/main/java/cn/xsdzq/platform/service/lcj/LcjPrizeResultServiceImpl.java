package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.LcjPrizeResultViewRepository;
import cn.xsdzq.platform.entity.lcj.LcjPrizeResultViewEntity;

@Service(value = "lcjPrizeRecordServiceImpl")
@Transactional(readOnly = true)
public class LcjPrizeResultServiceImpl implements LcjPrizeResultService{
	@Autowired
	private LcjPrizeResultViewRepository myPrizeRecordRepository;
	

	@Override
	public List<LcjPrizeResultViewEntity> getAllPrizeRecord(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myPrizeRecordRepository.count();
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(
			Date beginDate, Date endDate, String PrizeCode, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(beginDate, endDate, PrizeCode, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String PrizeCode, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(beginDate, endDate, PrizeCode, ClientId);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(beginDate, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqual(beginDate);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualOrderByRecordTimeDesc(endDate, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqual(endDate);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByPrizeCodeOrderByRecordTime(String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByPrizeCodeOrderByRecordTimeDesc(PrizeCode, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeCode(String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeCode(PrizeCode);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByClientIdOrderByRecordTime(String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByClientIdOrderByRecordTimeDesc(ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientId(String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByClientId(ClientId);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(
			Date beginDate, Date endDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(beginDate, endDate, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate,
			String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTimeDesc(beginDate, PrizeCode, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndPrizeCode(beginDate, PrizeCode);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(Date beginDate,
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTimeDesc(beginDate, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndClientId(beginDate, ClientId);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(Date endDate,
			String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(endDate, PrizeCode, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeCode(Date endDate, String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndPrizeCode(endDate, PrizeCode);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date endDate,
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(endDate, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndClientId(endDate, ClientId);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTime(String PrizeCode, String ClientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByPrizeCodeAndClientIdOrderByRecordTimeDesc(PrizeCode, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeCodeAndClientId(String PrizeCode, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeCodeAndClientId(PrizeCode, ClientId);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(
			Date beginDate, Date endDate, String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(beginDate,endDate, PrizeCode, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate,
			String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(beginDate,endDate, PrizeCode);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(
			Date beginDate, String PrizeCode, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(beginDate, PrizeCode, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate, String PrizeCode,
			String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(beginDate, PrizeCode, ClientId);
	}
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date endDate,
			String PrizeCode, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(endDate, PrizeCode, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date endDate, String PrizeCode, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(endDate, PrizeCode, ClientId);
	}
	
	@Override
	public List<LcjPrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(
			Date beginDate, Date endDate, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(beginDate,endDate, ClientId, pageable);
		List<LcjPrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate,
			Date endDate, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(beginDate, endDate, ClientId);
	}
}
