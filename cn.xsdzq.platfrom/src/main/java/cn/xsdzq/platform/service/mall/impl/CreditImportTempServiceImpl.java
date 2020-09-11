package cn.xsdzq.platform.service.mall.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.CreditImportTempRepository;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.service.mall.CreditImportTempService;

@Service(value = "creditImportTempServiceImpl")
@Transactional(readOnly = true)
public class CreditImportTempServiceImpl implements CreditImportTempService{
	private static final Logger logger = LoggerFactory.getLogger(CreditImportTempServiceImpl.class);
	@Autowired
	private CreditImportTempRepository creditImportTempRepository;	
	
	@Override
	public List<CreditImportTempEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditImportTempEntity> pages = creditImportTempRepository.findByOrderByBeginDateDesc(pageRequest);
			
		List<CreditImportTempEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) creditImportTempRepository.count();
	}
	@Override
	public List<CreditImportTempEntity> findByClientIdOrderByBeginDateDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditImportTempEntity> pages = creditImportTempRepository.findByClientIdOrderByBeginDateDesc(clientId,pageRequest);
			
		List<CreditImportTempEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientId(String clientId) {
		// TODO Auto-generated method stub
		return creditImportTempRepository.countByClientId(clientId);
	}
	@Override
	@Transactional
	public void add(CreditImportTempEntity entity) {
		// TODO Auto-generated method stub
		creditImportTempRepository.save(entity);
	
	}
	@Override
	@Transactional
	public void deleteAllTemp() {
		// TODO Auto-generated method stub
		creditImportTempRepository.deleteAll();
	}
	@Override
	public List<CreditImportTempEntity> findAllTemp() {
		// TODO Auto-generated method stub
		List<CreditImportTempEntity> infos = (List<CreditImportTempEntity>) creditImportTempRepository.findAll();
		return infos;
	}
}
