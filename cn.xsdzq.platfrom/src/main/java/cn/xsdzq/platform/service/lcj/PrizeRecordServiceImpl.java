package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyPrizeRecordRepository;
import cn.xsdzq.platform.dao.lcj.PagePrizeNumRepository;
import cn.xsdzq.platform.dao.lcj.PagePrizeRecordRepository;
import cn.xsdzq.platform.dao.lcj.PrizeResultRepository;
import cn.xsdzq.platform.entity.lcj.PrizeNumberEntity;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;

@Service(value = "prizeRecordServiceImpl")
@Transactional(readOnly = true)
public class PrizeRecordServiceImpl implements PrizeRecordService{
	@Autowired
	private MyPrizeRecordRepository myPrizeRecordRepository;
	
	@Autowired
	private PrizeResultRepository prizeResultRepository;
	
	@Autowired
	private PagePrizeRecordRepository pagePrizeRecordRepository;
	
	@Autowired
	private PagePrizeNumRepository pagePrizeNumRepository;

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
	//
	@Override
	public List<PrizeRecordEntity> getAllPrizeRecordForKmh(int pageNumber, int pageSize){
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeOrderByRecordTimeDesc(true, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countPrizeRecodAll() {
		return pagePrizeRecordRepository.countByType(true);
	}
	@Override
	public List<PrizeRecordEntity> findRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(
			String clientName, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeAndUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByRecordTimeDesc(true, clientName, clientId, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName, String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeRecordRepository.countByTypeAndUserEntity_clientNameLikeAndUserEntity_clientIdLike(true, clientName, clientId);
	}
	@Override
	public List<PrizeRecordEntity> findRecordByUserEntity_clientNameLikeOrderByNumberDesc(String clientName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeAndUserEntity_clientNameLikeOrderByRecordTimeDesc(true, clientName, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countRecordByUserEntity_clientNameLike(String clientName) {
		// TODO Auto-generated method stub
		return pagePrizeRecordRepository.countByTypeAndUserEntity_clientNameLike(true, clientName);
	}
	@Override
	public List<PrizeRecordEntity> findRecordByUserEntity_clientIdLikeOrderByNumberDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeAndUserEntity_clientIdLikeOrderByRecordTimeDesc(true, clientId, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countRecordByUserEntity_clientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeRecordRepository.countByTypeAndUserEntity_clientIdLike(true, clientId);
	}
	
	//
	@Override
	public int countPrizeNumberForKmh() {
		// TODO Auto-generated method stub
		return (int)pagePrizeNumRepository.count();
	}
	@Override
	public List<PrizeNumberEntity> getPrizeNumberForKmh(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeNumberEntity> pages = pagePrizeNumRepository.findByOrderByNumberDesc(pageable);
		List<PrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public List<PrizeNumberEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(
			String clientName, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeNumberEntity> pages = pagePrizeNumRepository.findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc( clientName,  clientId,pageable);
		List<PrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName, String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeNumRepository.countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(clientName, clientId);
	}
	@Override
	public List<PrizeNumberEntity> findByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeNumberEntity> pages = pagePrizeNumRepository.findByUserEntity_clientNameLikeOrderByNumberDesc(clientName, pageable);
		List<PrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientNameLike(String clientName) {
		// TODO Auto-generated method stub
		return pagePrizeNumRepository.countByUserEntity_clientNameLike(clientName);
	}
	@Override
	public List<PrizeNumberEntity> findByUserEntity_clientIdLikeOrderByNumberDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeNumberEntity> pages = pagePrizeNumRepository.findByUserEntity_clientIdLikeOrderByNumberDesc(clientId, pageable);
		List<PrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeNumRepository.countByUserEntity_clientIdLike(clientId);
	}
		
}
