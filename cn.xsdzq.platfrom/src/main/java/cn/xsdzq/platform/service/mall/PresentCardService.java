package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.model.mall.PresentCardDTO;


public interface PresentCardService {

	public void addPresentCard(PresentCardEntity presentCard);

	public List<PresentCardEntity> getPresentCardEntities();
	public void deletePresentCard(PresentCardDTO dto);
	public PresentCardEntity findById(long id);

}
