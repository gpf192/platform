package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.InfoEntity;

public interface MyInfoRepository extends PagingAndSortingRepository<InfoEntity, Long> {

	List<InfoEntity> findInfoEntityByCategoryId(long categoryId);

	int countInfoEntityByCategoryIdAndCreatedBy(long categoryId, String username);
	int countInfoEntityByCategoryId(long categoryId);

	@Modifying(clearAutomatically = true)
	@Query("update InfoEntity i set i.pageView = i.pageView + 1 where i.id=?1")
	@Transactional
	void addPageView(Long id);

	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultOrderByWeightDescModifytimeDesc(long categoryId,
			String checked_result, Pageable pageable);

	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByOrderByModifytimeDesc(long categoryId, String username,
			Pageable pageable);
	//add by fan

	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByAndCheckedResultOrderByModifytimeDesc(long categoryId, String username,
			String approveResult, Pageable pageable);
	//add by fan for all info 2018年10月9日15:46:08
	Page<InfoEntity> findInfoEntityByCreatedByAndCheckedResultOrderByModifytimeDesc(String username, String approveResult,
			Pageable pageable);
	Page<InfoEntity> findInfoEntityByCreatedByOrderByModifytimeDesc(String username, 
			Pageable pageable);
	int countInfoEntityByCreatedBy(String username);
	int countInfoEntityByCreatedByAndCheckedResult(String username, String approveResult);
	//根据标题关键字模糊查询
	Page<InfoEntity> findInfoEntityByCreatedByAndTitleLikeOrderByModifytimeDesc(String username, String title,
			Pageable pageable);
	
	Page<InfoEntity> findInfoEntityByCreatedByAndCheckedResultAndTitleLikeOrderByModifytimeDesc(String username, String approveResult, String title,
			 Pageable pageable);
	
	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByAndTitleLikeOrderByModifytimeDesc(long categoryId, String username, String title,
			 Pageable pageable);
	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByAndCheckedResultAndTitleLikeOrderByModifytimeDesc(long categoryId, String username, String title,
			 String approveResult, Pageable pageable);
	int countInfoEntityByCreatedByAndTitleLike(String userName, String title);
	int countInfoEntityByCreatedByAndCheckedResultAndTitleLike(String userName,  String approveResult, String title);
	int countInfoEntityByCategoryIdAndCreatedByAndCheckedResult(long categoryId, String userName, String approveResult);
	int countInfoEntityByCategoryIdAndCreatedByAndTitleLike(long categoryId, String userName, String title);
	int countInfoEntityByCategoryIdAndCreatedByAndCheckedResultAndTitleLike(long categoryId, String userName, String approveResult, String title);
	//超级用户
	Page<InfoEntity> findByOrderByModifytimeDesc(Pageable pageable);
	Page<InfoEntity> findInfoEntityByCheckedResultOrderByModifytimeDesc(String approveResult,
			Pageable pageable);
	int countInfoEntityByCheckedResult(String approveResult);
	Page<InfoEntity> findInfoEntityByTitleLikeOrderByModifytimeDesc(String title,
			Pageable pageable);
	int countInfoEntityByTitleLike(String title);
	Page<InfoEntity> findInfoEntityByCheckedResultAndTitleLikeOrderByModifytimeDesc(String approveResult, String title,
			 Pageable pageable);
	int countInfoEntityByCheckedResultAndTitleLike(String approveResult, String title);
	Page<InfoEntity> findInfoEntityByCategoryIdOrderByModifytimeDesc(long categoryId, 
			Pageable pageable);
	//int countInfoEntityByCategoryId(long categoryId);
	Page<InfoEntity> findInfoEntityByCategoryIdOrderByWeightDescModifytimeDesc(long categoryId, 
			Pageable pageable);
	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultOrderByModifytimeDesc(long categoryId,
			String approveResult, Pageable pageable);
	int countInfoEntityByCategoryIdAndCheckedResult(long categoryId, String approveResult);
	Page<InfoEntity> findInfoEntityByCategoryIdAndTitleLikeOrderByModifytimeDesc(long categoryId, String title,
			 Pageable pageable);
	int countInfoEntityByCategoryIdAndTitleLike(long categoryId,  String title);
	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultAndTitleLikeOrderByModifytimeDesc(long categoryId, String title,
			 String approveResult, Pageable pageable);
	int countInfoEntityByCategoryIdAndCheckedResultAndTitleLike(long categoryId, String approveResult, String title);

}
