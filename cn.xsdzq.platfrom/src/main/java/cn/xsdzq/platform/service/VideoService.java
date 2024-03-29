package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.VideoEntity;
import cn.xsdzq.platform.model.VideoId;

public interface VideoService {

	VideoEntity getCurrentVideoEntity(int fundType);

	void addVideoPageNumber(VideoId videoId);

	void addVideoPlayNumber(VideoId videoId);

	//cms
	void addVideo(VideoEntity videoEntity);
	void deleteVideo(long id);
	List<VideoEntity> findAll(int fundType,int pageNumber, int pageSize);
	int coutAll(int fundType);
}
