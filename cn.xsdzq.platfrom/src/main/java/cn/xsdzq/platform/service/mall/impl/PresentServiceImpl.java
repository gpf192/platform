package cn.xsdzq.platform.service.mall.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PresentRepository;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.service.mall.PresentService;



@Service(value = "presentServiceImpl")
@Transactional(readOnly = true)
public class PresentServiceImpl implements PresentService {

	@Autowired
	private PresentRepository presentRepository;

	@Override
	@Transactional
	public void addPresent(PresentEntity present) {
		// TODO Auto-generated method stub
		/*PresentEntity p = presentRepository.findById(present.getId()).get();
		present.setCreatetime(p.getCreatetime());
		present.setModifytime(new Date());*/
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
	public List<PresentEntity> getPresentEntitiesByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		return presentRepository.findByCategoryId(categoryId);
	}

	@Override
	@Transactional
	public void deletePresent(long id) {
		// TODO Auto-generated method stub
		PresentEntity p = presentRepository.findById(id).get();
		presentRepository.delete(p);
	}

	@Override
	public PresentEntity findById(long id) {
		PresentEntity p = presentRepository.findById(id).get();
		return p;
	}

}
