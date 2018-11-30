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

	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByOrderByWeightDescModifytimeDesc(long categoryId, String username,
			Pageable pageable);
	//add by fan

	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByAndCheckedResultOrderByWeightDescModifytimeDesc(long categoryId, String username,
			String approveResult, Pageable pageable);
	//add by fan for all info 2018年10月9日15:46:08
	Page<InfoEntity> findInfoEntityByCreatedByAndCheckedResultOrderByWeightDescModifytimeDesc(String username, String approveResult,
			Pageable pageable);
	Page<InfoEntity> findInfoEntityByCreatedByOrderByWeightDescModifytimeDesc(String username, 
			Pageable pageable);
	int countInfoEntityByCreatedBy(String username);
	int countInfoEntityByCreatedByAndCheckedResult(String username, String approveResult);
	//根据标题关键字模糊查询
	Page<InfoEntity> findInfoEntityByCreatedByAndTitleLikeOrderByWeightDescModifytimeDesc(String username, String title,
			Pageable pageable);
	
	Page<InfoEntity> findInfoEntityByCreatedByAndCheckedResultAndTitleLikeOrderByWeightDescModifytimeDesc(String username, String approveResult, String title,
			 Pageable pageable);
	
	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByAndTitleLikeOrderByWeightDescModifytimeDesc(long categoryId, String username, String title,
			 Pageable pageable);
	Page<InfoEntity> findInfoEntityByCategoryIdAndCreatedByAndCheckedResultAndTitleLikeOrderByWeightDescModifytimeDesc(long categoryId, String username, String title,
			 String approveResult, Pageable pageable);
	int countInfoEntityByCreatedByAndTitleLike(String userName, String title);
	int countInfoEntityByCreatedByAndCheckedResultAndTitleLike(String userName,  String approveResult, String title);
	int countInfoEntityByCategoryIdAndCreatedByAndCheckedResult(long categoryId, String userName, String approveResult);
	int countInfoEntityByCategoryIdAndCreatedByAndTitleLike(long categoryId, String userName, String title);
	int countInfoEntityByCategoryIdAndCreatedByAndCheckedResultAndTitleLike(long categoryId, String userName, String approveResult, String title);
	//超级用户
	Page<InfoEntity> findByOrderByWeightDescModifytimeDesc(Pageable pageable);
	Page<InfoEntity> findInfoEntityByCheckedResultOrderByWeightDescModifytimeDesc(String approveResult,
			Pageable pageable);
	int countInfoEntityByCheckedResult(String approveResult);
	Page<InfoEntity> findInfoEntityByTitleLikeOrderByWeightDescModifytimeDesc(String title,
			Pageable pageable);
	int countInfoEntityByTitleLike(String title);
	Page<InfoEntity> findInfoEntityByCheckedResultAndTitleLikeOrderByWeightDescModifytimeDesc(String approveResult, String title,
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
	Page<InfoEntity> findInfoEntityByCategoryIdAndTitleLikeOrderByWeightDescModifytimeDesc(long categoryId, String title,
			 Pageable pageable);
	int countInfoEntityByCategoryIdAndTitleLike(long categoryId,  String title);
	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultAndTitleLikeOrderByWeightDescModifytimeDesc(long categoryId, String title,
			 String approveResult, Pageable pageable);
	int countInfoEntityByCategoryIdAndCheckedResultAndTitleLike(long categoryId, String approveResult, String title);
//审核模块用到的
	Page<InfoEntity> findByCheckedResultInOrderByModifytimeDesc(List<String> list, Pageable pageable);
	int countInfoEntityByCheckedResultIn(List<String> list);
	Page<InfoEntity> findInfoEntityByTitleLikeAndCheckedResultInOrderByModifytimeDesc(String title,List<String> list, Pageable pageable);
	int countInfoEntityByTitleLikeAndCheckedResultIn(String title,List<String> list);
	Page<InfoEntity> findInfoEntityByCategoryIdAndCheckedResultInOrderByModifytimeDesc(long categoryId, List<String> list, Pageable pageable);
	int countInfoEntityByCategoryIdAndCheckedResultIn(long categoryId, List<String> list);
	Page<InfoEntity> findInfoEntityByCategoryIdAndTitleLikeAndCheckedResultInOrderByModifytimeDesc(long id, String title, List<String> list, Pageable pageable);
	int countInfoEntityByCategoryIdAndTitleLikeAndCheckedResultIn(long categoryId, String title, List<String> list);
	//h5
	List<InfoEntity> findInfoEntityByCommonFlagAndCheckedResultOrderByWeightDescModifytimeDesc(String commonFlag, String checkedResult, Pageable pageable);
	int countInfoEntityByCommonFlagAndCheckedResult(String commonFlag, String checkedResult);
	List<InfoEntity> findInfoEntityByCategoryIdAndCheckedResult(long categoryId, String checkedResult);

}
