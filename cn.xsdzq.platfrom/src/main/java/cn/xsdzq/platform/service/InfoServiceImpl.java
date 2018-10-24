package cn.xsdzq.platform.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.controller.InfoController;
import cn.xsdzq.platform.dao.InfoRepository;
import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.util.UserManageUtil;

@Service(value = "infoServiceImpl")
@Transactional(readOnly = true)
public class InfoServiceImpl implements IInfoService {
	Logger logger = LogManager.getLogger(InfoServiceImpl.class.getName());
	@Autowired
	private InfoRepository infoRepository;

	@Override
	public InfoEntity getInfo(long id) {
		// TODO Auto-generated method stub
		InfoEntity info = infoRepository.getInfo(id);
		return info;
	}

	@Override
	@Transactional
	public void addInfo(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		infoRepository.addInfo(infoEntity);
	}

	@Override
	public List<InfoEntity> getInfosByCategoryId(long id) {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = infoRepository.getInfosByCategoryId(id);
		return infos;
	}

	@Override
	public List<InfoEntity> getInfosByCategoryIdToFront(long id) {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = infoRepository.getInfosByCategoryIdToFront(id);
		return infos;
	}

	@Override
	public List<InfoEntity> searchInfos(String key) {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = infoRepository.searchInfos(key);
		//如果关联的栏目不显示前端，剔除
		Iterator<InfoEntity> it = infos.iterator();
		while (it.hasNext()) {		
			InfoEntity info = it.next();
			CategoryEntity cate = info.getCategoryEntity();
			if(!cate.getDisplayFlag()) {
				it.remove();
			}		
		}
		return infos;
	}

	@Override
	@Transactional
	public void deleteInfo(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		infoRepository.deleteInfo(infoEntity);
	}

	@Override
	@Transactional
	public void modifyInfo(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		//撤回的时候录入时间未赋值
		infoEntity.setCreatetime(infoRepository.getInfo(infoEntity.getId()).getCreatetime()); 
		infoRepository.modifyInfo(infoEntity);
	}

	@Override
	@Transactional
	public void addPageViewById(Long id) {
		// TODO Auto-generated method stub
		infoRepository.addPageView(id);
	}

	@Override
	@Transactional
	public void addWeight(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		infoRepository.addWeight(infoEntity);
	}

	// add by fjx
	@Override
	public List<InfoEntity> searUncheckchInfos() {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = infoRepository.searUncheckchInfos();
		return infos;
	}

	@Override
	public String getCheckResult(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void modifyCheckResult(long id, boolean flag, String action) {
		// TODO Auto-generated method stub
		InfoEntity info = infoRepository.getInfo(id);
		String tempCheckedResult = info.getCheckedResult();
		System.out.println(info.getId() + "  " + info.getChecked() + "       " + info.getCheckedResult());
		//区分三个动作，前台分页 选中多页的进行操作的问题,批量同意，单个拒绝，批量撤回，
		if("approve".equals(action) && flag && tempCheckedResult != "approve") {
			//System.out.println("审核命令  +  待审核*******************************************");		
			info.setCheckedResult("approve");
			User user = UserManageUtil.getUser();
			String name = user.getUsername();
			info.setApprovedBy(name);
			info.setChecked("Y");// 设置为已审核
			info.setApproveTime(new Date());
			infoRepository.modifyInfo(info);
		}
		
		if("approve".equals(action) && flag == false && tempCheckedResult != "approve") {
			//System.out.println("退回命令  +  待审核*******************************************");		
			info.setCheckedResult("generate");
			User user = UserManageUtil.getUser();
			String name = user.getUsername();
			info.setApprovedBy(name);
			info.setChecked("Y");// 设置为已审核
			info.setApproveTime(new Date());
			infoRepository.modifyInfo(info);
		}
		
		
		if("callback".equals(action) && flag == false && "approve".equals(tempCheckedResult)) {
			//System.out.println("撤回命令  +  已发布*******************************************");		
			info.setCheckedResult("generate");
			User user = UserManageUtil.getUser();
			String name = user.getUsername();
			info.setApprovedBy(name);
			info.setChecked("Y");// 设置为已审核
			logger.info(name + "  撤回操作 ,文章id为： "+info.getId());
			infoRepository.modifyInfo(info);
		}
		
		
	}
}
