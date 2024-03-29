package cn.xsdzq.platform.service.mall.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.CreditCategoryRepository;
import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.PageCreditRepository;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.service.mall.CreditService;

@Service(value = "creditServiceImpl")
@Transactional(readOnly = true)
public class CreditServiceImpl implements CreditService{
	private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

	@Autowired
	private CreditCategoryRepository creditCategoryRepository;
	
	@Autowired
	private PageCreditRepository pageCreditRepository;
	
	@Autowired
	private CreditRecordRepository creditRecordRepository;
	
	
	@Override
	@Transactional
	public void addCredit(CreditEntity entity) {
		// TODO Auto-generated method stub
		//判断是否有关联导入积分明细，若有则更新明细中的前端项目显示字段，包括手动减积分
		//List<CreditRecordEntity> recordList = creditRecordRepository.findByTypeAndItemCode(true, entity.getCategoryCode());
		List<CreditRecordEntity> recordList = creditRecordRepository.findByItemCode(entity.getCategoryCode());

		if(recordList.size() >0) {
			for(CreditRecordEntity record:recordList) {
				record.setItem(entity.getFrontName());
				creditRecordRepository.save(record);
			}			
		}
		entity.setCreatetime(new Date());
		creditCategoryRepository.save(entity);
	}

	@Override
	public List<CreditEntity> getCreditEntities() {
		// TODO Auto-generated method stub
		return creditCategoryRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteCredit(long id) {
		// TODO Auto-generated method stub
		CreditEntity entity = creditCategoryRepository.findById(id).get();
		creditCategoryRepository.delete(entity);
	}

	@Override
	public CreditEntity getByCategoryCode(String code) {
		// TODO Auto-generated method stub
		return creditCategoryRepository.findByCategoryCode(code);
	}
	// 分页查询
	@Override
	public List<CreditEntity> findByOrderByCategoryCodeDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditEntity> pages = pageCreditRepository.findByCategoryNameNotOrderByCategoryCodeDesc("全部",pageRequest);	
		List<CreditEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return pageCreditRepository.countByCategoryNameNot("全部");	
	}

	@Override
	public List<CreditEntity> findByCategoryCodeLikeOrderByCategoryCodeDesc(String code, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditEntity> pages = pageCreditRepository.findByCategoryCodeLikeAndCategoryNameNotOrderByCategoryCodeDesc(code,"全部", pageRequest);	
		List<CreditEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCategoryCodeLike(String code) {
		// TODO Auto-generated method stub
		return pageCreditRepository.countByCategoryCodeLikeAndCategoryNameNot(code,"全部");
	}

	@Override
	public List<CreditEntity> findByCategoryNameLikeOrderByCategoryCodeDesc(String name, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditEntity> pages = pageCreditRepository.findByCategoryNameLikeAndCategoryNameNotOrderByCategoryCodeDesc(name,"全部", pageRequest);	
		List<CreditEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCategoryNameLike(String name) {
		// TODO Auto-generated method stub
		return pageCreditRepository.countByCategoryNameLikeAndCategoryNameNot(name,"全部");
	}


	@Override
	public List<CreditEntity> findByCategoryNameLikeAndCategoryCodeLikeOrderByCategoryCodeDesc(String name, String code,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CreditEntity> pages = pageCreditRepository.findByCategoryNameLikeAndCategoryCodeLikeOrderByCategoryCodeDesc(name, code,"全部",pageRequest);	
		List<CreditEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCategoryNameLikeAndCategoryCodeLike(String name, String code) {
		// TODO Auto-generated method stub
		return pageCreditRepository.countByCategoryNameLikeAndCategoryCodeLikeAndCategoryNameNot(name, code,"全部");
	}

}
