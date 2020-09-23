package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PagePresentRecordRepository;
import cn.xsdzq.platform.dao.mall.PresentRecordRepository;
import cn.xsdzq.platform.entity.mall.PresentRecordEntity;
import cn.xsdzq.platform.service.mall.PresentRecordService;


@Service(value = "presentRecordServiceImpl")
@Transactional(readOnly = true)
public class PresentRecordServiceImpl implements PresentRecordService {

	@Autowired
	private PresentRecordRepository presentRecordRepository;
	
	@Autowired
	private PagePresentRecordRepository pagePresentRecordRepository;

	@Override
	public List<PresentRecordEntity> getRecordEntities() {
		// TODO Auto-generated method stub
		return presentRecordRepository.findAll();
	}
	
	//分页
		@Override
		public List<PresentRecordEntity> findByOrderByRecordTimeDesc(int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByOrderByRecordTimeDesc(pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countAll() {
			// TODO Auto-generated method stub
			return (int) pagePresentRecordRepository.count();
		}

		@Override
		public List<PresentRecordEntity> findByPresentCardEntity_presentIdOrderByRecordTimeDesc(long presentId,
				int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByPresentCardEntity_presentIdOrderByRecordTimeDesc(presentId, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByPresentCardEntity_presentId(long presentId) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByPresentCardEntity_presentId(presentId);
		}

		@Override
		public List<PresentRecordEntity> findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(String clientId,
				int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(clientId, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByMallUserEntity_clientIdLike(String clientId) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByMallUserEntity_clientIdLike(clientId);

		}

		@Override
		public List<PresentRecordEntity> findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(String mobile, int pageNumber,
				int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(mobile, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByMallUserEntity_mobileLike(String mobile) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByMallUserEntity_mobileLike(mobile);

		}

		@Override
		public List<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(
				long presentId, String clientId, int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc( presentId,  clientId, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(long presentId, String clientId) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(presentId,clientId);

		}

		@Override
		public List<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(
				long presentId, String mobile, int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc( presentId,  mobile, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(long presentId, String mobile) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(presentId,mobile);

		}

		@Override
		public List<PresentRecordEntity> findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(
				String clientId, String mobile, int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc( clientId,  mobile, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(String clientId, String mobile) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(clientId,mobile);

		}

		@Override
		public List<PresentRecordEntity> findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(
				long presentId, String clientId, String mobile, int pageNumber, int pageSize) {
			// TODO Auto-generated method stub
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			Page<PresentRecordEntity> pages = pagePresentRecordRepository.findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc( presentId, clientId,  mobile, pageRequest);			
			List<PresentRecordEntity> infos = pages.getContent();
			return infos;
		}

		@Override
		public int countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(
				long presentId, String clientId, String mobile) {
			// TODO Auto-generated method stub
			return pagePresentRecordRepository.countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(presentId,clientId,mobile);

		}

}
