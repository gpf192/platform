package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.InfoRepository;
import cn.xsdzq.platform.entity.InfoEntity;

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

}
