package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PageCreditRecordRepository;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.service.mall.CreditImportRecordService;

@Service(value = "creditImportRecordServiceImpl")
@Transactional(readOnly = true)
public class CreditImportRecordServiceImpl implements CreditImportRecordService{
	private static final Logger logger = LoggerFactory.getLogger(CreditImportRecordServiceImpl.class);
	@Autowired
	private PageCreditRecordRepository creditImportRecordRepository;
	@Override
	public List<CreditRecordEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByOrderByBeginDateDesc(pageRequest);
			
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) creditImportRecordRepository.count();
	}
	@Override
	public List<CreditRecordEntity> findByClientIdOrderByBeginDateDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditRecordEntity> pages = creditImportRecordRepository.findByMallUserEntity_ClientIdOrderByBeginDateDesc(clientId,pageRequest);
			
		List<CreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return creditImportRecordRepository.countByMallUserEntity_ClientId(clientId);
	}
	@Override
	@Transactional
	public void addRecord(CreditRecordEntity entity) {
		// TODO Auto-generated method stub
		creditImportRecordRepository.save(entity);
	}
}
