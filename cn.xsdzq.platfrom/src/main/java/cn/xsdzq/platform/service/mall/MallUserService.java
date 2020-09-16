package cn.xsdzq.platform.service.mall;



import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;

public interface MallUserService {


	public MallUserEntity getUserByClientId(String clientId);

	public void addMallUser(MallUserEntity mallUserEntity);

	public void addCreditScore(CreditImportTempEntity temp);
	
	//分页查询
	List<MallUserInfoEntity> findByOrderByCreditScoreDesc(int pageNumber, int pageSize);
	int countAll();
	
	List<MallUserInfoEntity> findByClientIdOrderByCreditScoreDesc(String clientId, int pageNumber, int pageSize);
	int countByClientId(String clientId);

}
