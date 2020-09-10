package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditEntity;
public interface CreditService {
	public void addCredit(CreditEntity entity);

	public List<CreditEntity> getCreditEntities();
	public void deleteCredit(long id);
}
