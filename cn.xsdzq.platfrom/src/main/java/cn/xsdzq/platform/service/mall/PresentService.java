package cn.xsdzq.platform.service.mall;

import java.util.List;

import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.entity.PresentEntity;

public interface PresentService {

	public void addPresent(PresentEntity present);

	public List<PresentEntity> getPresentEntities();
	public List<PresentEntity> getPresentEntitiesByName(String name);
	public List<PresentEntity> getPresentEntitiesByCategory(PresentCategoryEntity entity);
	public void deletePresent(PresentEntity present);

}
