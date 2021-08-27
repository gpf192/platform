package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.LcjPrizeNumRepository;
import cn.xsdzq.platform.dao.lcj.LcjPrizeRecordRepository;
import cn.xsdzq.platform.entity.lcj.LcjPrizeNumberEntity;
import cn.xsdzq.platform.entity.lcj.LcjPrizeRecordEntity;

@Service(value = "lcjPrizeRecordServiceImpl")
@Transactional(readOnly = true)
public class LciPrizeRecordServiceImpl implements LciPrizeRecordService{
	@Autowired
	private LcjPrizeRecordRepository pagePrizeRecordRepository;
	
	@Autowired
	private LcjPrizeNumRepository pagePrizeNumRepository;
	

	@Override
	public List<LcjPrizeRecordEntity> getAllPrizeRecordForKmh(int pageNumber, int pageSize){
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeOrderByRecordTimeDesc(true, pageable);
		List<LcjPrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countPrizeRecodAll() {
		return pagePrizeRecordRepository.countByType(true);
	}
	@Override
	public List<LcjPrizeRecordEntity> findRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(
			String clientName, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeAndUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByRecordTimeDesc(true, clientName, clientId, pageable);
		List<LcjPrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countRecordByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName, String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeRecordRepository.countByTypeAndUserEntity_clientNameLikeAndUserEntity_clientIdLike(true, clientName, clientId);
	}
	@Override
	public List<LcjPrizeRecordEntity> findRecordByUserEntity_clientNameLikeOrderByNumberDesc(String clientName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeAndUserEntity_clientNameLikeOrderByRecordTimeDesc(true, clientName, pageable);
		List<LcjPrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countRecordByUserEntity_clientNameLike(String clientName) {
		// TODO Auto-generated method stub
		return pagePrizeRecordRepository.countByTypeAndUserEntity_clientNameLike(true, clientName);
	}
	@Override
	public List<LcjPrizeRecordEntity> findRecordByUserEntity_clientIdLikeOrderByNumberDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeRecordEntity> pages = pagePrizeRecordRepository.findByTypeAndUserEntity_clientIdLikeOrderByRecordTimeDesc(true, clientId, pageable);
		List<LcjPrizeRecordEntity> infos = pages.getContent();
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
	public List<LcjPrizeNumberEntity> getPrizeNumberForKmh(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeNumberEntity> pages = pagePrizeNumRepository.findByOrderByNumberDesc(pageable);
		List<LcjPrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public List<LcjPrizeNumberEntity> findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc(
			String clientName, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeNumberEntity> pages = pagePrizeNumRepository.findByUserEntity_clientNameLikeAndUserEntity_clientIdLikeOrderByNumberDesc( clientName,  clientId,pageable);
		List<LcjPrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(String clientName, String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeNumRepository.countByUserEntity_clientNameLikeAndUserEntity_clientIdLike(clientName, clientId);
	}
	@Override
	public List<LcjPrizeNumberEntity> findByUserEntity_clientNameLikeOrderByNumberDesc(String clientName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeNumberEntity> pages = pagePrizeNumRepository.findByUserEntity_clientNameLikeOrderByNumberDesc(clientName, pageable);
		List<LcjPrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientNameLike(String clientName) {
		// TODO Auto-generated method stub
		return pagePrizeNumRepository.countByUserEntity_clientNameLike(clientName);
	}
	@Override
	public List<LcjPrizeNumberEntity> findByUserEntity_clientIdLikeOrderByNumberDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LcjPrizeNumberEntity> pages = pagePrizeNumRepository.findByUserEntity_clientIdLikeOrderByNumberDesc(clientId, pageable);
		List<LcjPrizeNumberEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return pagePrizeNumRepository.countByUserEntity_clientIdLike(clientId);
	}
}
