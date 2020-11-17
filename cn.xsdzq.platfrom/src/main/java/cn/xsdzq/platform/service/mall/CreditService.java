package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
public interface CreditService {
	public void addCredit(CreditEntity entity);

	public List<CreditEntity> getCreditEntities();
	public void deleteCredit(long id);
	
	public CreditEntity getByCategoryCode(String code);
	
	List<CreditEntity> findByOrderByCategoryCodeDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<CreditEntity> findByCategoryCodeLikeOrderByCategoryCodeDesc(String code, int pageNumber, int pageSize);
	int countByCategoryCodeLike(String code);
	
	List<CreditEntity> findByCategoryNameLikeOrderByCategoryCodeDesc(String name, int pageNumber, int pageSize);
	int countByCategoryNameLike(String name);
	
	List<CreditEntity> findByCategoryNameLikeAndCategoryCodeLikeOrderByCategoryCodeDesc(String name, String code, int pageNumber, int pageSize);
	int countByCategoryNameLikeAndCategoryCodeLike(String name, String code);
	
}
