package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.InfoEntity;

public interface IMyInfoService {

	InfoEntity getInfoEntityById(Long id);

	void addPageViewById(Long id);

	List<InfoEntity> getInfosByCategoryId(long id);

	List<InfoEntity> getInfosByCategoryId(long id, int pageNumber, int pageSize);

	List<InfoEntity> getInfosByCategoryIdByCreator(long id, String userName, int pageNumber, int pageSize);
	//add by fan 修复问题：后台非录入人员能显示分页栏，以及查询结果数，但是不显示结果，正常情况下需要结果录入人权限进行查询
	//  普通用户查询全部栏目，筛选条件：用户、审核状态
    List<InfoEntity> getInfosByCreatorByCheckedResult(String userName, String approveResult, int pageNumber, int pageSize);
    //  普通用户查询全部栏目，筛选条件：用户(审核状态为全部)
    List<InfoEntity> getInfosByCreator(String userName, int pageNumber, int pageSize);
    //普通用户，查询全部栏目时，全部审核状态，筛选条件：用户、标题关键字
    List<InfoEntity> getInfosByCreatorByTitleLike(String userName, String title, int pageNumber, int pageSize); 
    //审核状态非all，条件：用户名、title关键字、审核状态
    List<InfoEntity> getInfosByCreatorByCheckedResultByTitleLike(String userName, String approveResult, String title,int pageNumber, int pageSize); 
    
	List<InfoEntity> getInfosByCategoryIdByCreatorByCheckedResult(long id, String userName, String approveResult, int pageNumber, int pageSize);
	//审核状态为 all， 条件：用户名、栏目id、   title关键字 
	List<InfoEntity> getInfosByCategoryIdByCreatorByTitleLike(long id, String userName, String title, int pageNumber, int pageSize);
	List<InfoEntity> getInfosByCategoryIdByCreatorByCheckedResultByTitleLike(long id, String userName, String approveResult, String title, int pageNumber, int pageSize);

	int countInfosByCategoryIdByCreator(long categoryId, String userName);
	//前台仍保持原接口，根据类别id查询info，不需要跟用户关联
	int countInfosByCategoryId(long categoryId);
    int countInfosByCreator(String username);
    int countInfosByCreatorByCheckedResult(String userName, String approveResult);      
    int countInfosByCreatorByTitleLike(String userName, String title);
    int countInfosByCreatorByCheckedResultByTitleLike(String userName, String approveResult,String title);
    int countInfosByCategoryIdByCreatorByCheckedResult(long categoryId, String userName, String approveResult);
    int countInfosByCategoryIdByCreatorByTitleLike(long categoryId, String userName, String title);
	int countInfosByCategoryIdByCreatorByCheckedResultByTitleLike(long categoryId, String userName, String approveResult, String title);
	
	//以下为超级用户所需接口
	 List<InfoEntity> getInfosBySuperCreator(int pageNumber, int pageSize);
	 int countInfosBySuperCreator();
	 List<InfoEntity> getInfosByCheckedResult(String approveResult, int pageNumber, int pageSize);
	 int countInfosByCheckedResult(String approveResult);      
	 List<InfoEntity> getInfosByTitleLike(String title, int pageNumber, int pageSize); 
	 int countInfosByTitleLike(String title);
	 List<InfoEntity> getInfosByCheckedResultByTitleLike(String approveResult, String title,int pageNumber, int pageSize); 
	 int countInfosByCheckedResultByTitleLike(String approveResult,String title);
	 List<InfoEntity> getInfosByCategoryIdByCheckAll(long id, int pageNumber, int pageSize);
	 List<InfoEntity> getInfosByCategoryIdByCheckedResult(long id, String approveResult, int pageNumber, int pageSize);
	 int countInfosByCategoryIdByCheckedResult(long categoryId, String approveResult);
	 List<InfoEntity> getInfosByCategoryIdByTitleLike(long id, String title, int pageNumber, int pageSize);
	 int countInfosByCategoryIdByTitleLike(long categoryId, String title);
	 List<InfoEntity> getInfosByCategoryIdByCheckedResultByTitleLike(long id, String approveResult, String title, int pageNumber, int pageSize);
	 int countInfosByCategoryIdByCheckedResultByTitleLike(long categoryId, String approveResult, String title);
	 //审核模块  Check
	 List<InfoEntity> getCheckInfosBySuperCreator(int pageNumber, int pageSize);
	 int countCheckInfosBySuperCreator();
	 List<InfoEntity> getCheckInfosByTitleLike(String title, int pageNumber, int pageSize); 
	 int countCheckInfosByTitleLike(String title);
	 List<InfoEntity> getCheckInfosByCategoryIdByCheckAll(long id, int pageNumber, int pageSize);
	 int countCheckInfosByCategoryId(long categoryId);
	 List<InfoEntity> getCheckInfosByCategoryIdByTitleLike(long id, String title, int pageNumber, int pageSize);
	 int countCheckInfosByCategoryIdByTitleLike(long categoryId, String title);
	 //h5前端需要的接口
	 List<InfoEntity> getInfosByCommonFlag(String flag,int pageNumber, int pageSize);
	 List<InfoEntity> getInfosByCategoryIdByCheckedResultForH5(long id);
}
