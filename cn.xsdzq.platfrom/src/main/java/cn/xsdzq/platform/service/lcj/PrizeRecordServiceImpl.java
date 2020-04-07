package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyPrizeRecordRepository;
import cn.xsdzq.platform.dao.lcj.PrizeResultRepository;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;

@Service(value = "prizeRecordServiceImpl")
@Transactional(readOnly = true)
public class PrizeRecordServiceImpl implements PrizeRecordService{
	@Autowired
	private MyPrizeRecordRepository myPrizeRecordRepository;
	
	@Autowired
	private PrizeResultRepository prizeResultRepository;
	
	

	@Override
	public List<PrizeResultViewEntity> getAllPrizeRecord(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myPrizeRecordRepository.count();
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(
			Date beginDate, Date endDate, String PrizeCode, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(beginDate, endDate, PrizeCode, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date beginDate, Date endDate, String PrizeCode, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeAndClientId(beginDate, endDate, PrizeCode, ClientId);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(beginDate, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqual(beginDate);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualOrderByRecordTimeDesc(endDate, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqual(endDate);
	}
	@Override
	public List<PrizeResultViewEntity> findByPrizeCodeOrderByRecordTime(String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByPrizeCodeOrderByRecordTimeDesc(PrizeCode, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeCode(String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeCode(PrizeCode);
	}
	@Override
	public List<PrizeResultViewEntity> findByClientIdOrderByRecordTime(String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByClientIdOrderByRecordTimeDesc(ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientId(String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByClientId(ClientId);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(
			Date beginDate, Date endDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(beginDate, endDate, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTime(Date beginDate,
			String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndPrizeCodeOrderByRecordTimeDesc(beginDate, PrizeCode, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeCode(Date beginDate, String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndPrizeCode(beginDate, PrizeCode);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTime(Date beginDate,
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndClientIdOrderByRecordTimeDesc(beginDate, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndClientId(Date beginDate, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndClientId(beginDate, ClientId);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(Date endDate,
			String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(endDate, PrizeCode, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeCode(Date endDate, String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndPrizeCode(endDate, PrizeCode);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndClientIdOrderByRecordTime(Date endDate,
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(endDate, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndClientId(Date endDate, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndClientId(endDate, ClientId);
	}
	@Override
	public List<PrizeResultViewEntity> findByPrizeCodeAndClientIdOrderByRecordTime(String PrizeCode, String ClientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByPrizeCodeAndClientIdOrderByRecordTimeDesc(PrizeCode, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeCodeAndClientId(String PrizeCode, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeCodeAndClientId(PrizeCode, ClientId);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTime(
			Date beginDate, Date endDate, String PrizeCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCodeOrderByRecordTimeDesc(beginDate,endDate, PrizeCode, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(Date beginDate, Date endDate,
			String PrizeCode) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeCode(beginDate,endDate, PrizeCode);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(
			Date beginDate, String PrizeCode, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(beginDate, PrizeCode, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(Date beginDate, String PrizeCode,
			String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndPrizeCodeAndClientId(beginDate, PrizeCode, ClientId);
	}
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTime(Date endDate,
			String PrizeCode, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndPrizeCodeAndClientIdOrderByRecordTimeDesc(endDate, PrizeCode, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(Date endDate, String PrizeCode, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndPrizeCodeAndClientId(endDate, PrizeCode, ClientId);
	}
	
	@Override
	public List<PrizeResultViewEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTime(
			Date beginDate, Date endDate, String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultViewEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientIdOrderByRecordTimeDesc(beginDate,endDate, ClientId, pageable);
		List<PrizeResultViewEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(Date beginDate,
			Date endDate, String ClientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndClientId(beginDate, endDate, ClientId);
	}
}
