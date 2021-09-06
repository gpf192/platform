package cn.xsdzq.platform.service.mall;



import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.model.mall.UserIntegralDTO;

public interface MallUserService {

	public MallUserEntity getUserByClientId(String clientId);

	public void addMallUser(MallUserEntity mallUserEntity);

	public void addCreditScore(CreditImportTempEntity temp);
	public boolean modifyUserTotalIntegral(UserIntegralDTO dto);
	
	//分页查询
	List<MallUserInfoEntity> findByOrderByCreditScoreDesc(int pageNumber, int pageSize);
	int countAll();
	//
	List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeOrderByCreditScoreDesc(String username, int pageNumber, int pageSize);
	int countByMallUserEntity_clientNameLike(String username);
	
	List<MallUserInfoEntity> findByClientIdLikeOrderByCreditScoreDesc(String clientId, int pageNumber, int pageSize);
	int countByClientIdLike(String clientId);
	
	List<MallUserInfoEntity> findByMallUserEntity_moblieLikeOrderByCreditScoreDesc(String moblie, int pageNumber, int pageSize);
	int countByMallUserEntity_moblieLike(String moblie);
	//
	List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndClentIdLikeOrderByCreditScoreDesc(String username, String clientId, int pageNumber, int pageSize);
	int countByMallUserEntity_clientNameLikeAndClentIdLike(String username, String clientId);
	
	List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndMallUserEntity_moblieLikeOrderByCreditScoreDesc(String username, String moblie, int pageNumber, int pageSize);
	int countByMallUserEntity_clientNameLikeAndMallUserEntity_moblieLike(String username, String moblie);
	
	List<MallUserInfoEntity> findByClientIdLikeAndMallUserEntity_moblieLikeOrderByCreditScoreDesc(String clientId, String moblie, int pageNumber, int pageSize);
	int countByClientIdLikeAndMallUserEntity_moblieLike(String clientId, String moblie);
	//
	List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndClentIdLikeAndMallUserEntity_moblieLikeOrderByCreditScoreDesc(String username, String clientId, String moblie,int pageNumber, int pageSize);
	int countByMallUserEntity_clientNameLikeAndClentIdLikeAndMallUserEntity_moblieLike(String username, String clientId, String moblie);
	//
	MallUserInfoEntity findByClientId(String clientId);
	
	//跑批失效分数
	public void endDateJob();
	
	//跑批失效卡券
	public void cardEndDateJob();
	
	public void scanCrmCreditJob();
	//邮件发送任务
	public void mailSendCreditJob();
	//手动再次执行
	public void scanCrmErrorManual();
		
}
