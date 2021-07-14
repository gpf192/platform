package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.PageVideoRepository;
import cn.xsdzq.platform.dao.VideoRepository;
import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.entity.VideoEntity;
import cn.xsdzq.platform.exception.BusinessException;
import cn.xsdzq.platform.model.VideoId;

@Service
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private PageVideoRepository pageVideoRepository;

	@Override
	public VideoEntity getCurrentVideoEntity() {
		// TODO Auto-generated method stub
		List<VideoEntity> videoEntities = videoRepository.findByToogle(true);
		VideoEntity currentVideoEntity = null;
		if (videoEntities.size() > 0) {
			currentVideoEntity = videoEntities.get(0);
		} else {
			currentVideoEntity = videoRepository.findAll().get(0);
		}
		  VideoId videoId = new VideoId();
		  videoId.setId(currentVideoEntity.getId());
		  addVideoPageNumber(videoId);
		return currentVideoEntity;
	}

	@Override
	@Transactional
	public void addVideoPageNumber(VideoId videoId) {
		// TODO Auto-generated method stub
		VideoEntity videoEntity = videoRepository.findById(videoId.getId()).get();
		if (videoEntity == null) {
			throw new BusinessException("视频对象不存在");
		}
		videoEntity.setPageNumber(videoEntity.getPageNumber() + 1);
		videoRepository.save(videoEntity);

	}

	@Override
	@Transactional
	public void addVideoPlayNumber(VideoId videoId) {
		// TODO Auto-generated method stub
		VideoEntity videoEntity = videoRepository.findById(videoId.getId()).get();
		if (videoEntity == null) {
			throw new BusinessException("视频对象不存在");
		}
		videoEntity.setVideoPlayNumber(videoEntity.getVideoPlayNumber() + 1);
		videoRepository.save(videoEntity);
	}
//
	@Override
	public List<VideoEntity> findAll(int pageNumber, int pageSize) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<VideoEntity> pages = pageVideoRepository.findAll(pageRequest);
			
		List<VideoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int coutAll() {
		// TODO Auto-generated method stub
		return (int)pageVideoRepository.count();
	}



	@Override
	@Transactional
	public void addVideo(VideoEntity videoEntity) {
		// TODO Auto-generated method stub
		// 新建用户给予基础的权限
		pageVideoRepository.save(videoEntity);
		List<VideoEntity> list = pageVideoRepository.findByToogle(true);		
		if(list.size() > 1) {
			
			throw new RuntimeException("只允许开启一个基金视频，请关闭其他视频。");
		}
		
	}

	/*@Override
	@Transactional
	public void modifyVideo(VideoEntity videoEntity) {
		pageVideoRepository.(userEntity);
	}*/

	@Override
	@Transactional
	public void deleteVideo(long id) {
		// TODO Auto-generated method stub
		pageVideoRepository.deleteById(id);
	}
}
