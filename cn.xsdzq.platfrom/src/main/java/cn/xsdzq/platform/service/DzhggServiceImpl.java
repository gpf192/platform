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

	@Override
	public List<DzhggEntity> findByActivityAndNameAndPhoneOrderByRecordtimeDesc(String activity, String name,
			String phone, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByActivityAndNameLikeAndPhoneLikeOrderByRecordtimeDesc(activity, name, phone, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByActivityAndNameAndPhone(String activity, String name, String phone) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByActivityAndNameLikeAndPhoneLike(activity, name, phone);
	}

	@Override
	public List<DzhggEntity> findByActivityAndNameOrderByRecordtimeDesc(String activity, String name, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByActivityAndNameLikeOrderByRecordtimeDesc(activity, name, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByActivityAndName(String activity, String name) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByActivityAndNameLike(activity, name);
	}

	@Override
	public List<DzhggEntity> findByActivityAndPhoneOrderByRecordtimeDesc(String activity, String phone, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByActivityAndPhoneLikeOrderByRecordtimeDesc(activity, phone, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByActivityAndPhone(String activity, String phone) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByActivityAndPhoneLike(activity, phone);
	}

	@Override
	public List<DzhggEntity> findByNameAndPhoneOrderByRecordtimeDesc(String name, String phone, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByNameLikeAndPhoneLikeOrderByRecordtimeDesc(name, phone, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByNameAndPhone(String name, String phone) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByNameLikeAndPhoneLike(name, phone);
	}

	@Override
	public List<DzhggEntity> findByActivityOrderByRecordtimeDesc(String activity, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByActivityOrderByRecordtimeDesc(activity, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByActivity(String activity) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByActivity(activity);
	}

	@Override
	public List<DzhggEntity> findByNameOrderByRecordtimeDesc(String name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByNameLikeOrderByRecordtimeDesc(name, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByName(String name) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByNameLike(name);
	}

	@Override
	public List<DzhggEntity> findByPhoneOrderByRecordtimeDesc(String phone, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<DzhggEntity> pages = myDzhggRepository.findByPhoneLikeOrderByRecordtimeDesc(phone, pageRequest);
		List<DzhggEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPhone(String phone) {
		// TODO Auto-generated method stub
		return myDzhggRepository.countByPhoneLike(phone);
	}
}
