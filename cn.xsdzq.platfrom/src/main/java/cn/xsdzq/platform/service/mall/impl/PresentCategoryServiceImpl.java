package cn.xsdzq.platform.service.mall.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.PresentCategoryRepository;
import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.model.PresentCategory;
import com.xsdzq.mall.service.PresentCategoryService;

@Service
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
		presentCategoryEntity.setFlag(presentCategory.isFlag());
		presentCategoryRepository.save(presentCategoryEntity);
	}

	@Override
	public List<PresentCategoryEntity> getCategoryEntities() {
		// TODO Auto-generated method stub
		logger.info("findAll");
		return presentCategoryRepository.findAll();
	}


	@Override
	public void deletePresentCategory(PresentCategory presentCategory) {
		// TODO Auto-generated method stub
		PresentCategoryEntity entity = new PresentCategoryEntity();
		entity.setId(presentCategory.getId());
		entity.setName(presentCategory.getName());
		entity.setFlag(presentCategory.isFlag());
		
		presentCategoryRepository.delete(entity);
	}

}
