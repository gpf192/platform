package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentEntity;


public interface PresentService {

	public void addPresent(PresentEntity present);

	public List<PresentEntity> getPresentEntities();
	public List<PresentEntity> getPresentEntitiesByName(String name);
	public PresentEntity getPresentEntitiesByCode(String code);
	public List<PresentEntity> getPresentEntitiesByCategoryId(long categoryId) ;
	public void deletePresent(long id);
	public PresentEntity findById(long id);
	//分页查询
	List<PresentEntity> findByOrderByCreatetimeDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<PresentEntity> findByNameOrderByCreatetimeDesc(String  name , int pageNumber, int pageSize);
	int countByName(String name);
	
	List<PresentEntity> findByPresentCategory_nameOrderByCreatetimeDesc(String  categoryName , int pageNumber, int pageSize);
	int countByPresentCategory_name(String categoryName);
	
	List<PresentEntity> findByNameAndPresentCategory_nameOrderByCreatetimeDesc(String  name ,String categoryName, int pageNumber, int pageSize);
	int countByNameAndPresentCategory_name(String name,String categoryName);
}
