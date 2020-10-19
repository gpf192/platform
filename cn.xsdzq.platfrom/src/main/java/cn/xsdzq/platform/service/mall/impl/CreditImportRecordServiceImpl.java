package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.PageCreditRecordRepository;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.service.mall.CreditImportRecordService;

@Service(value = "creditImportRecordServiceImpl")
@Transactional(readOnly = true)
public class CreditImportRecordServiceImpl implements CreditImportRecordService{
	private static final Logger logger = LoggerFactory.getLogger(CreditImportRecordServiceImpl.class);
	@Autowired
	private PageCreditRecordRepository creditImportRecordRepository;
	
	@Autowired
	private CreditRecordRepository creditRecordRepository;
	
	@Override
	@Transactional
	public void addRecord(CreditRecordEntity entity) {
		// TODO Auto-generated method stub
		creditImportRecordRepository.save(entity);
	}
	//分页查询
	@Override
	public List<CreditRecordEntity> findByOrderByRecordTimeDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByOrderByRecordTimeDesc(pageRequest);
			
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) creditImportRecordRepository.count();
	}
	//
	@Override
	public List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeOrderByRecordTimeDesc(String username, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByMallUserEntity_ClientNameLikeOrderByRecordTimeDesc(username,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByMallUserEntity_ClientNameLike(String username) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByMallUserEntity_ClientNameLike(username);
	}
	@Override
	public List<CreditRecordEntity> findByClientIdLikeOrderByRecordTimeDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByClientIdLikeOrderByRecordTimeDesc(clientId,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByClientIdLike(clientId);

	}
	@Override
	public List<CreditRecordEntity> findByItemCodeLikeOrderByRecordTimeDesc(String itemCode, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByItemCodeLikeOrderByRecordTimeDesc(itemCode,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByItemCodeLike(String itemCode) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByItemCodeLike(itemCode);

	}
	//
	@Override
	public List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndClientIdLikeOrderByRecordTimeDesc(
			String username, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByMallUserEntity_ClientNameLikeAndClientIdLikeOrderByRecordTimeDesc(username,  clientId,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByMallUserEntity_ClientNameLikeAndClientIdLike(String username, String clientId) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByMallUserEntity_ClientNameLikeAndClientIdLike(username, clientId);

	}
	@Override
	public List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndItemCodeLikeOrderByRecordTimeDesc(
			String username, String itemCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByMallUserEntity_ClientNameLikeAndItemCodeLikeOrderByRecordTimeDesc(username,  itemCode,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
		}
	@Override
	public int countByMallUserEntity_ClientNameLikeAndItemCodeLike(String username, String itemCode) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByMallUserEntity_ClientNameLikeAndClientIdLike(username, itemCode);

	}
	@Override
	public List<CreditRecordEntity> findByClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(String clientId,
			String itemCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(clientId,  itemCode,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientIdLikeAndItemCodeLike(String clientId, String itemCode) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByClientIdLikeAndItemCodeLike(clientId, itemCode);

	}
	//
	@Override
	public List<CreditRecordEntity> findByMallUserEntity_ClientNameLikeAndClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(
			String username, String clientId, String itemCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByMallUserEntity_ClientNameLikeAndClientIdLikeAndItemCodeLikeOrderByRecordTimeDesc(username,  clientId,itemCode,pageRequest);	
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByMallUserEntity_ClientNameLikeAndClientIdLikeItemCodeLike(String username, String clientId,
			String itemCode) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByMallUserEntity_ClientNameLikeAndClientIdLikeAndItemCodeLike(username,clientId, itemCode);

	}
	@Override
	public int countByClientIdAndItemCodeAndBeginDateAndType(
			String clientId, String itemCode,  String beginDate) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByClientIdAndItemCodeAndBeginDateAndType(clientId, itemCode,  beginDate,true);
	}
	@Override
	public int countByTypeAndItemCode(boolean type, String itemCode) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByTypeAndItemCode(type, itemCode);
	}
	@Override
	public List<CreditRecordEntity> findByType(boolean type) {
		// TODO Auto-generated method stub
		return creditRecordRepository.findByType(type);
	}
	@Override
	public List<CreditRecordEntity> findByEndDateAndType(int endDate, boolean type) {
		// TODO Auto-generated method stub
		return creditRecordRepository.findByEndDateAndType(endDate, type);
	}
	@Override
	public List<CreditRecordEntity> findByEndDateLessThanEqualAndType(int endDate, boolean type) {
		// TODO Auto-generated method stub
		return creditRecordRepository.findByEndDateLessThanEqualAndType(endDate, type);
	}
	@Override
	public List<CreditRecordEntity> findByClientIdAndType(int endDate, boolean type) {
		// TODO Auto-generated method stub
		return creditRecordRepository.findByClientIdAndType(endDate, type);
	}
	
}
