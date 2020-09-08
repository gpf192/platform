package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentEntity;


public interface PresentService {

	public void addPresent(PresentEntity present);

	public List<PresentEntity> getPresentEntities();
	public List<PresentEntity> getPresentEntitiesByName(String name);
	public List<PresentEntity> getPresentEntitiesByCategoryId(long categoryId) ;
	public void deletePresent(long id);
	public PresentEntity findById(long id);
}
