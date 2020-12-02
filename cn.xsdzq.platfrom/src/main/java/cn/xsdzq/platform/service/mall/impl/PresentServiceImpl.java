package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PagePresentRepository;
import cn.xsdzq.platform.dao.mall.PresentCategoryRepository;
import cn.xsdzq.platform.dao.mall.PresentRepository;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.service.mall.PresentService;



@Service(value = "presentServiceImpl")
@Transactional(readOnly = true)
public class PresentServiceImpl implements PresentService {

	@Autowired
	private PresentRepository presentRepository;
	
	@Autowired
	private PagePresentRepository pagePresentRepository;
	@Autowired
	private PresentCategoryRepository presentCategoryRepository;

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
	public PresentEntity getPresentEntitiesByCode(String code) {
		// TODO Auto-generated method stub
		return presentRepository.findByCode(code);
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
		Page<PresentEntity> pages = pagePresentRepository.findByNameNotOrderByCreatetimeDesc("全部",pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return  pagePresentRepository.countByNameNot("全部");
	}
//
	@Override
	public List<PresentEntity> findByNameOrderByCreatetimeDesc(String name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByNameLikeAndNameNotOrderByCreatetimeDesc(name, "全部",pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByName(String name) {
		// TODO Auto-generated method stub
		return pagePresentRepository.countByNameLikeAndNameNot(name,"全部");
	}
//
	@Override
	public List<PresentEntity> findByPresentCategory_nameOrderByCreatetimeDesc(String categoryName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByPresentCategory_codeLikeAndNameNotOrderByCreatetimeDesc(categoryName,"全部", pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentCategory_name(String categoryName) {
		// TODO Auto-generated method stub
		return pagePresentRepository.countByPresentCategory_codeLikeAndNameNot(categoryName,"全部");
	}
//
	@Override
	public List<PresentEntity> findByNameAndPresentCategory_nameOrderByCreatetimeDesc(String name, String categoryName,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentEntity> pages = pagePresentRepository.findByNameLikeAndPresentCategory_codeLikeAndNameNotOrderByCreatetimeDesc(name,categoryName,"全部", pageRequest);	
		List<PresentEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByNameAndPresentCategory_name(String name, String categoryName) {
		// TODO Auto-generated method stub
		return pagePresentRepository.countByNameLikeAndPresentCategory_codeLikeAndNameNot(name,categoryName,"全部");
	}

}
