package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;


public interface PresentCardService {

	public void addPresentCard(PresentCardEntity presentCard);

	public List<PresentCardEntity> getPresentCardEntities();

}
