package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.PresentRepository;
import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.service.PresentService;

@Service
@Transactional(readOnly = true)
public class PresentServiceImpl implements PresentService {

	@Autowired
	private PresentRepository presentRepository;

	@Override
	@Transactional
	public void addPresent(PresentEntity present) {
		// TODO Auto-generated method stub

		presentRepository.save(present);

	}

	@Override
	public List<PresentEntity> getPresentEntities() {
		// TODO Auto-generated method stub
		return presentRepository.findAll();
	}

	@Override
	public List<PresentEntity> getPresentEntitiesByName(String name) {
		// TODO Auto-generated method stub
		return presentRepository.findByName(name);
	}

	@Override
	public List<PresentEntity> getPresentEntitiesByCategory(PresentCategoryEntity entity) {
		// TODO Auto-generated method stub
		return presentRepository.findByPresentCategoryEntity(entity);
	}

	@Override
	@Transactional
	public void deletePresent(PresentEntity present) {
		// TODO Auto-generated method stub
		presentRepository.delete(present);
	}

}
