package cn.xsdzq.platform.service.mall.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PresentCategoryRepository;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.model.mall.PresentCategory;
import cn.xsdzq.platform.service.mall.PresentCategoryService;



@Service(value = "presentCategoryServiceImpl")
@Transactional(readOnly = true)
public class PresentCategoryServiceImpl implements PresentCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(PresentCategoryServiceImpl.class);

	@Autowired
	private PresentCategoryRepository presentCategoryRepository;

	@Override
	@Transactional
	public void addPresentCategory(PresentCategory presentCategory) {
		// TODO Auto-generated method stub

		PresentCategoryEntity presentCategoryEntity = new PresentCategoryEntity();
		presentCategoryEntity.setName(presentCategory.getName());
		presentCategoryEntity.setFlag(presentCategory.getFlag());
		presentCategoryRepository.save(presentCategoryEntity);
	}

	@Override
	public List<PresentCategoryEntity> getCategoryEntities() {
		// TODO Auto-generated method stub
		logger.info("findAll");
		return presentCategoryRepository.findAll();
	}


	@Override
	@Transactional
	public void deletePresentCategory(PresentCategory presentCategory) {
		// TODO Auto-generated method stub
		PresentCategoryEntity entity = new PresentCategoryEntity();
		entity.setId(presentCategory.getId());
		entity.setName(presentCategory.getName());
		entity.setFlag(presentCategory.getFlag());
		
		presentCategoryRepository.delete(entity);
	}

	@Override
	public PresentCategoryEntity findById(long id) {
		// TODO Auto-generated method stub
		PresentCategoryEntity p = presentCategoryRepository.findById(id).get();
		
		return p;
	}

}
