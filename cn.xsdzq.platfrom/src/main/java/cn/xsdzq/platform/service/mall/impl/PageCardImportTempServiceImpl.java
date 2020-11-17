package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PageCardImportTempRepository;
import cn.xsdzq.platform.entity.mall.CardImportTempEntity;
import cn.xsdzq.platform.service.mall.PageCardImportTempService;

@Service(value = "pageCardImportTempServiceImpl")
@Transactional(readOnly = true)
public class PageCardImportTempServiceImpl implements PageCardImportTempService{
	private static final Logger logger = LoggerFactory.getLogger(PageCardImportTempServiceImpl.class);
	@Autowired
	private PageCardImportTempRepository pageCardImportTempRepository;
	
	@Override
	@Transactional
	public void add(CardImportTempEntity entity) {
		// TODO Auto-generated method stub
		pageCardImportTempRepository.save(entity);
	}
	@Override
	@Transactional
	public void deleteAllTemp() {
		// TODO Auto-generated method stub
		pageCardImportTempRepository.deleteAll();
	}
	@Override
	public List<CardImportTempEntity> findAllTemp() {
		// TODO Auto-generated method stub
		List<CardImportTempEntity> infos = (List<CardImportTempEntity>) pageCardImportTempRepository.findAll();
		return infos;
	}
	
	@Override
	public List<CardImportTempEntity> findByOrderByCardId(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CardImportTempEntity> pages = pageCardImportTempRepository.findByOrderByCardId(pageRequest);		
		List<CardImportTempEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pageCardImportTempRepository.count();
	}	
	
}
