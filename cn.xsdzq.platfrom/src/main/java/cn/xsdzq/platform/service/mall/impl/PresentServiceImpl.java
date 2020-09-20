package cn.xsdzq.platform.service.mall.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PagePresentRepository;
import cn.xsdzq.platform.dao.mall.PresentRepository;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.service.mall.PresentService;



@Service(value = "presentServiceImpl")
@Transactional(readOnly = true)
public class PresentServiceImpl implements PresentService {

	@Autowired
	private PresentRepository presentRepository;
	
	@Autowired
	private PagePresentRepository pagePresentRepository;

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
//分页查询 
	@Override
	public List<PresentEntity> findByOrderByCreatetimeDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByOrderByCreatetimeDesc(pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pagePresentRepository.count();
	}
//
	@Override
	public List<PresentEntity> findByNameOrderByCreatetimeDesc(String name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByNameOrderByCreatetimeDesc(name, pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByName(String name) {
		// TODO Auto-generated method stub
		return pagePresentRepository.countByName(name);
	}
//
	@Override
	public List<PresentEntity> findByPresentCategory_nameOrderByCreatetimeDesc(String categoryName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByPresentCategory_nameOrderByCreatetimeDesc(categoryName, pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentCategory_name(String categoryName) {
		// TODO Auto-generated method stub
		return pagePresentRepository.countByPresentCategory_name(categoryName);
	}
//
	@Override
	public List<PresentEntity> findByNameAndPresentCategory_nameOrderByCreatetimeDesc(String name, String categoryName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByNameAndPresentCategory_nameOrderByCreatetimeDesc(name,categoryName, pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByNameAndPresentCategory_name(String name, String categoryName) {
		// TODO Auto-generated method stub
		return pagePresentRepository.countByNameAndPresentCategory_name(name,categoryName);
	}

}
