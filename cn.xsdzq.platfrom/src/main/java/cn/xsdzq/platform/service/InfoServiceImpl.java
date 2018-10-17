package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.InfoRepository;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.util.UserManageUtil;

@Service(value = "infoServiceImpl")
@Transactional(readOnly = true)
public class InfoServiceImpl implements IInfoService {
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
	public void modifyCheckResult(long id, boolean flag) {
		// TODO Auto-generated method stub
		InfoEntity info = infoRepository.getInfo(id);
		System.out.println(info.getId() + "         " + info.getChecked() + "       " + info.getCheckedResult());
		info.setChecked("Y");// 设置为已审核
		if (flag) {
			// 如果审核通过，审核结果置位approve 否则为reject
			info.setCheckedResult("approve");
		} else {
			info.setCheckedResult("generate");
		}
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		info.setApprovedBy(name);
		System.out.println(info.getId() + "         " + info.getChecked() + "       " + info.getCheckedResult());
		infoRepository.modifyInfo(info);
	}

}
