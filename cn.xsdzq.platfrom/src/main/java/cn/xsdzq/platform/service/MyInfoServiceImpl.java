package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public List<InfoEntity> getInfosByCategoryId(long id) {
		// TODO Auto-generated method stub
		List<InfoEntity> infos = myInfoRepository.findInfoEntityByCategoryId(id);
		return infos;
	}

	@Override
	public List<InfoEntity> getInfosByCategoryId(long id, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<InfoEntity> pages = myInfoRepository.findInfoEntityByCategoryId(id, pageRequest);
		List<InfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countInfosByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		return myInfoRepository.countInfoEntityByCategoryId(categoryId);
	}

}
