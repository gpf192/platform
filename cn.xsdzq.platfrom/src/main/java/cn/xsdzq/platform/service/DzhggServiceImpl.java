package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.MyDzhggRepository;
import cn.xsdzq.platform.entity.DzhggEntity;

@Service(value = "dzhggServiceImpl")
@Transactional(readOnly = true)
public class DzhggServiceImpl implements DzhggService{
	@Autowired
	private MyDzhggRepository myDzhggRepository;

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myDzhggRepository.count();
	}

	@Override
	public List<DzhggEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByOrderByRecordtimeDesc(pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}
}
