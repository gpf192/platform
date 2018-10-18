package cn.xsdzq.platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.MyInfoRepository;
import cn.xsdzq.platform.entity.InfoEntity;

@Service(value = "myInfoServiceImpl")
@Transactional(readOnly = true)
public class MyInfoServiceImpl implements IMyInfoService {
	@Autowired
	private MyInfoRepository myInfoRepository;

	@Override
	@Transactional
	public void addPageViewById(Long id) {
		// TODO Auto-generated method stub
		myInfoRepository.addPageView(id);
	}

	@Override
	public InfoEntity getInfoEntityById(Long id) {
		// TODO Auto-generated method stub
		Optional<InfoEntity> optional = myInfoRepository.findById(id);
		return optional.get();
	}

	@Override
	public List<InfoEntity> getInfosByCategoryId(long id) {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = myInfoRepository.findInfoEntityByCategoryId(id);
		return infos;
	}

	@Override
	public List<InfoEntity> getInfosByCategoryId(long id, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository
				.findInfoEntityByCategoryIdAndCheckedResultOrderByWeightDescModifytimeDesc(id, "approve", pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<InfoEntity> getInfosByCategoryIdByCreator(long id, String userName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCreatedByOrderByModifytimeDesc(id,
				userName, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	//add by fan
	@Override
	public List<InfoEntity> getInfosByCategoryIdByCreatorByCheckedResult(long id, String userName, String approveResult, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCreatedByAndCheckedResultOrderByModifytimeDesc(id,
				userName, approveResult, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	
	//add by fan  2018年10月9日11:08:29
	@Override
	public List<InfoEntity> getInfosByCreatorByCheckedResult(String userName, String approveResult, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		//Page<InfoEntity> pages = myInfoRepository.findAll(pageRequest);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCreatedByAndCheckedResultOrderByModifytimeDesc(userName, approveResult, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}	
	
	public List<InfoEntity> getInfosByCreator(String userName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		//Page<InfoEntity> pages = myInfoRepository.findAll(pageRequest);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCreatedByOrderByModifytimeDesc(userName, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}	
	//当普通用户 查询 全部栏目时，只查用户下的info，用此接口
	@Override
	public int countInfosByCreator(String username) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCreatedBy(username);
	}
	public int countInfosByCreatorByCheckedResult(String username, String approveResult) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCreatedByAndCheckedResult(username, approveResult);
	}
	//当普通用户 查询 非全部 栏目时，同事关联类别id 和 用户名下，用此接口
	@Override
	public int countInfosByCategoryIdByCreator(long categoryId, String userName) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCategoryIdAndCreatedBy(categoryId, userName);
	}
	//前台查询，与用户权限无关，仍用一期的接口
	@Override
	public int countInfosByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCategoryId(categoryId);
	}

	public List<InfoEntity> getInfosByCreatorByTitleLike(String userName, String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCreatedByAndTitleLikeOrderByModifytimeDesc(userName, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;		
	}
	public List<InfoEntity> getInfosByCreatorByCheckedResultByTitleLike(String userName, String approveResult, String title,  int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCreatedByAndCheckedResultAndTitleLikeOrderByModifytimeDesc(userName, approveResult, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;		
	}
	public List<InfoEntity> getInfosByCategoryIdByCreatorByTitleLike(long id, String userName, String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCreatedByAndTitleLikeOrderByModifytimeDesc(id, userName, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public List<InfoEntity> getInfosByCategoryIdByCreatorByCheckedResultByTitleLike(long id, String userName, String approveResult, String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCreatedByAndCheckedResultAndTitleLikeOrderByModifytimeDesc(id, userName, approveResult, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countInfosByCreatorByTitleLike(String userName, String title) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCreatedByAndTitleLike(userName, title);
	}
	
	public int countInfosByCreatorByCheckedResultByTitleLike(String userName, String approveResult, String title) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCreatedByAndCheckedResultAndTitleLike(userName, approveResult, title);
	}
	public int countInfosByCategoryIdByCreatorByCheckedResult(long categoryId, String userName, String approveResult) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCategoryIdAndCreatedByAndCheckedResult(categoryId, userName, approveResult);
	}
	public int countInfosByCategoryIdByCreatorByTitleLike(long categoryId, String userName, String title) {
		return myInfoRepository.countInfoEntityByCategoryIdAndCreatedByAndTitleLike(categoryId, userName, title);
	}
	public int countInfosByCategoryIdByCreatorByCheckedResultByTitleLike(long categoryId, String userName, String approveResult, String title) {
		return myInfoRepository.countInfoEntityByCategoryIdAndCreatedByAndCheckedResultAndTitleLike(categoryId, userName, approveResult, title);
	}
	//超级用户
	public List<InfoEntity> getInfosBySuperCreator(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		//Page<InfoEntity> pages = myInfoRepository.findAll(pageRequest);//问题 ：无法排序
		Page<InfoEntity> pages = myInfoRepository.findByOrderByModifytimeDesc(pageRequest);//问题 ：无法排序
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countInfosBySuperCreator() {
		// TODO Auto-generated method stub
		 return (int) myInfoRepository.count();
	}
	public List<InfoEntity> getInfosByCheckedResult(String approveResult, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCheckedResultOrderByModifytimeDesc(approveResult, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}	
	public int countInfosByCheckedResult(String approveResult) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCheckedResult(approveResult);
	}
	public List<InfoEntity> getInfosByTitleLike(String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByTitleLikeOrderByModifytimeDesc(title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;		
	}
	public int countInfosByTitleLike(String title) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByTitleLike(title);
	}
	public List<InfoEntity> getInfosByCheckedResultByTitleLike(String approveResult, String title,  int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCheckedResultAndTitleLikeOrderByModifytimeDesc(approveResult, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;		
	}
	public int countInfosByCheckedResultByTitleLike(String approveResult, String title) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCheckedResultAndTitleLike(approveResult, title);
	}
	public List<InfoEntity> getInfosByCategoryIdByCheckAll(long id, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdOrderByWeightDescModifytimeDesc(id, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public List<InfoEntity> getInfosByCategoryIdByCheckedResult(long id, String approveResult, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCheckedResultOrderByModifytimeDesc(id,
				approveResult, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countInfosByCategoryIdByCheckedResult(long categoryId,  String approveResult) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCategoryIdAndCheckedResult(categoryId, approveResult);
	}
	public List<InfoEntity> getInfosByCategoryIdByTitleLike(long id, String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndTitleLikeOrderByModifytimeDesc(id, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countInfosByCategoryIdByTitleLike(long categoryId, String title) {
		return myInfoRepository.countInfoEntityByCategoryIdAndTitleLike(categoryId, title);
	}
	public List<InfoEntity> getInfosByCategoryIdByCheckedResultByTitleLike(long id, String approveResult, String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCheckedResultAndTitleLikeOrderByModifytimeDesc(id , approveResult, title, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countInfosByCategoryIdByCheckedResultByTitleLike(long categoryId, String approveResult, String title) {
		return myInfoRepository.countInfoEntityByCategoryIdAndCheckedResultAndTitleLike(categoryId, approveResult, title);
	}
	//审核模块所需要用到的 
	public List<String> getCheckConditions(){
		List<String> list = new ArrayList<>();
		list.add("approve");
		list.add("submit");
		return list;
	}
	public List<InfoEntity> getCheckInfosBySuperCreator(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		List<String> list = getCheckConditions();
		Page<InfoEntity> pages = myInfoRepository.findByCheckedResultInOrderByModifytimeDesc(list, pageRequest);//问题 ：无法排序
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countCheckInfosBySuperCreator() {
		// TODO Auto-generated method stub
		List<String> list = getCheckConditions();
		return (int) myInfoRepository.countInfoEntityByCheckedResultIn(list);
	}
	public List<InfoEntity> getCheckInfosByTitleLike(String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		List<String> list = getCheckConditions();
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByTitleLikeAndCheckedResultInOrderByModifytimeDesc(title, list, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;		
	}
	public int countCheckInfosByTitleLike(String title) {
		// TODO Auto-generated method stub
		List<String> list = getCheckConditions();
		return myInfoRepository.countInfoEntityByTitleLikeAndCheckedResultIn(title,list);
	}
	public List<InfoEntity> getCheckInfosByCategoryIdByCheckAll(long id, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub  Check
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		List<String> list = getCheckConditions();
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndCheckedResultInOrderByModifytimeDesc(id, list, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countCheckInfosByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		List<String> list = getCheckConditions();
		return myInfoRepository.countInfoEntityByCategoryIdAndCheckedResultIn(categoryId, list);
	}
	public List<InfoEntity> getCheckInfosByCategoryIdByTitleLike(long id, String title, int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		List<String> list = getCheckConditions();
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryIdAndTitleLikeAndCheckedResultInOrderByModifytimeDesc(id, title, list, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}
	public int countCheckInfosByCategoryIdByTitleLike(long categoryId, String title) {
		List<String> list = getCheckConditions();
		return myInfoRepository.countInfoEntityByCategoryIdAndTitleLikeAndCheckedResultIn(categoryId, title, list);
	}
	public List<InfoEntity> getInfosByCommonFlag(String flag,int pageNumber, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		List<InfoEntity> infos = myInfoRepository.findInfoEntityByCommonFlagAndCheckedResult(flag, "approve",pageRequest);
		return infos;
	}
	public int countInfosByCommonFlag(String flag){
		int sum = myInfoRepository.countInfoEntityByCommonFlagAndCheckedResult(flag, "approve");
		return sum;
	}
	public List<InfoEntity> getInfosByCategoryIdByCheckedResultForH5(long id) {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = myInfoRepository.findInfoEntityByCategoryIdAndCheckedResult(id, "approve");
		return infos;
	}
}
