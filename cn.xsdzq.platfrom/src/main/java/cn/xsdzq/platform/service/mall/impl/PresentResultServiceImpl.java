package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PagePresentResultRepository;
import cn.xsdzq.platform.dao.mall.PresentResultRepository;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentResultEntity;
import cn.xsdzq.platform.service.mall.PresentResultService;


@Service(value = "presentResultServiceImpl")
@Transactional(readOnly = true)
public class PresentResultServiceImpl implements PresentResultService {

	@Autowired
	private PresentResultRepository presentResultRepository;

	@Autowired
	private PagePresentResultRepository pagePresentResultRepository;
	
	@Override
	public List<PresentResultEntity> getResultEntities() {
		// TODO Auto-generated method stub
		return presentResultRepository.findAll();
	}
	//分页
	@Override
	public List<PresentResultEntity> findByOrderByRecordTimeDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByOrderByRecordTimeDesc(pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pagePresentResultRepository.count();
	}

	@Override
	public List<PresentResultEntity> findByPresentCardEntity_presentIdOrderByRecordTimeDesc(long presentId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByPresentCardEntity_presentIdOrderByRecordTimeDesc(presentId, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentCardEntity_presentId(long presentId) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByPresentCardEntity_presentId(presentId);
	}

	@Override
	public List<PresentResultEntity> findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(String clientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(clientId, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_clientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByMallUserEntity_clientIdLike(clientId);

	}

	@Override
	public List<PresentResultEntity> findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(String mobile, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(mobile, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_mobileLike(String mobile) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByMallUserEntity_mobileLike(mobile);

	}

	@Override
	public List<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(
			long presentId, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc( presentId,  clientId, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(long presentId, String clientId) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(presentId,clientId);

	}

	@Override
	public List<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(
			long presentId, String mobile, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc( presentId,  mobile, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(long presentId, String mobile) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(presentId,mobile);

	}

	@Override
	public List<PresentResultEntity> findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(
			String clientId, String mobile, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc( clientId,  mobile, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(String clientId, String mobile) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(clientId,mobile);

	}

	@Override
	public List<PresentResultEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(
			long presentId, String clientId, String mobile, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentResultEntity> pages = pagePresentResultRepository.findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc( presentId, clientId,  mobile, pageRequest);			
		List<PresentResultEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(
			long presentId, String clientId, String mobile) {
		// TODO Auto-generated method stub
		return pagePresentResultRepository.countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(presentId,clientId,mobile);

	}

}
