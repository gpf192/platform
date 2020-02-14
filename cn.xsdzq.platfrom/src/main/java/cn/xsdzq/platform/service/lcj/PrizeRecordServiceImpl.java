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

@Service(value = "prizeRecordServiceImpl")
@Transactional(readOnly = true)
public class PrizeRecordServiceImpl implements PrizeRecordService{
	@Autowired
	private MyPrizeRecordRepository myPrizeRecordRepository;
	
	@Autowired
	private PrizeResultRepository prizeResultRepository;
	
	
	
	@Override
	public List<PrizeResultEntity> getAllPrizeRecord(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myPrizeRecordRepository.count();
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(
			Date beginDate, Date endDate, String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate, endDate, PrizeEntity_name, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate, Date endDate, String PrizeEntity_name, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(beginDate, endDate, PrizeEntity_name, UserEntity_clientId);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(beginDate, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqual(beginDate);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualOrderByRecordTimeDesc(endDate, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqual(endDate);
	}
	@Override
	public List<PrizeResultEntity> findByPrizeEntity_nameOrderByRecordTime(String PrizeEntity_name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByPrizeEntity_nameOrderByRecordTimeDesc(PrizeEntity_name, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeEntity_name(String PrizeEntity_name) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeEntity_name(PrizeEntity_name);
	}
	@Override
	public List<PrizeResultEntity> findByUserEntity_clientIdOrderByRecordTime(String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByUserEntity_clientIdOrderByRecordTimeDesc(UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientId(String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByUserEntity_clientId(UserEntity_clientId);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(
			Date beginDate, Date endDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(beginDate, endDate, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameOrderByRecordTime(Date beginDate,
			String PrizeEntity_name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndPrizeEntity_nameOrderByRecordTimeDesc(beginDate, PrizeEntity_name, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeEntity_name(Date beginDate, String PrizeEntity_name) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndPrizeEntity_name(beginDate, PrizeEntity_name);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate,
			String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndUserEntity_clientId(beginDate, UserEntity_clientId);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime(Date endDate,
			String PrizeEntity_name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTimeDesc(endDate, PrizeEntity_name, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeEntity_name(Date endDate, String PrizeEntity_name) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndPrizeEntity_name(endDate, PrizeEntity_name);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date endDate,
			String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(endDate, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndUserEntity_clientId(endDate, UserEntity_clientId);
	}
	@Override
	public List<PrizeResultEntity> findByPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(String PrizeEntity_name, String UserEntity_clientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(PrizeEntity_name, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeEntity_nameAndUserEntity_clientId(String PrizeEntity_name, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeEntity_nameAndUserEntity_clientId(PrizeEntity_name, UserEntity_clientId);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTime(
			Date beginDate, Date endDate, String PrizeEntity_name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_nameOrderByRecordTimeDesc(beginDate,endDate, PrizeEntity_name, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_name(Date beginDate, Date endDate,
			String PrizeEntity_name) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndPrizeEntity_name(beginDate,endDate, PrizeEntity_name);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(
			Date beginDate, String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate, PrizeEntity_name, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date beginDate, String PrizeEntity_name,
			String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndPrizeEntity_nameAndUserEntity_clientId(beginDate, PrizeEntity_name, UserEntity_clientId);
	}
	@Override
	public List<PrizeResultEntity> findByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTime(Date endDate,
			String PrizeEntity_name, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientIdOrderByRecordTimeDesc(endDate, PrizeEntity_name, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(Date endDate, String PrizeEntity_name, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeLessThanEqualAndPrizeEntity_nameAndUserEntity_clientId(endDate, PrizeEntity_name, UserEntity_clientId);
	}
	
	@Override
	public List<PrizeResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(
			Date beginDate, Date endDate, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeResultEntity> pages = myPrizeRecordRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate,endDate, UserEntity_clientId, pageable);
		List<PrizeResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(Date beginDate,
			Date endDate, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(beginDate, endDate, UserEntity_clientId);
	}
}
