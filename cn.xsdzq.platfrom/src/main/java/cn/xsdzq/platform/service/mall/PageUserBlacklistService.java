package cn.xsdzq.platform.service.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.UserBlackListEntity;
import cn.xsdzq.platform.entity.mall.UserBlackListImportEntity;

public interface PageUserBlacklistService {
	List<UserBlackListEntity> getAllUserBlacklist(int pageNumber, int pageSize);
	int countAll();
	
	public List<UserBlackListEntity> findByClientIdLikeOrderByCreateDateDesc(String clientId, int pageNumber, int pageSize);
	public int coutByClientIdLike(String clientId);
	
	public List<UserBlackListEntity> findByClientNameLikeOrderByCreateDateDesc(String clientName, int pageNumber, int pageSize);
	public int coutByClientNameLike(String clientName);
	
	public List<UserBlackListEntity> findByClientIdLikeAndClientNameLikeOrderByCreateDateDesc(String clientId,String clientName, int pageNumber, int pageSize);
	public int coutByClientIdLikeAndClientNameLike(String clientId,String clientName);
	
	//增删改
	public void add(UserBlackListEntity serBlackListEntity);
	public void delete(long id);
	//导入
	List<UserBlackListImportEntity> getAllUserBlacklistTemp(int pageNumber, int pageSize);
	int countAllTemp();
	
	public void addImport(UserBlackListImportEntity userBlackListImportEntity);
	public void deleteAllImport();
	List<UserBlackListImportEntity> getAllUserBlacklistImport();
	List<UserBlackListEntity> getUserBlacklistByClientId(String clientId);
	
}
