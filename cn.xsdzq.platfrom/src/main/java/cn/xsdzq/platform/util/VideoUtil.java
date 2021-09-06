package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.VideoEntity;
import cn.xsdzq.platform.model.VideoDTO;

public class VideoUtil {
	public static VideoDTO convertVideoDTOByEntityToMain(VideoEntity entity) {
		VideoDTO dto = new VideoDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStartTime("20210101");
		dto.setFundCode(entity.getFundCode());
		dto.setPageNumber(entity.getPageNumber());
		dto.setVideoPlayNumber(entity.getVideoPlayNumber());
		dto.setVideoUrl(entity.getVideoUrl());
		dto.setToogle(entity.isToogle());
		dto.setContent(entity.getContent());
		dto.setFundType(entity.getFundType());
		return dto;
	}
	
	public static VideoEntity convertVideoEntityByDTO(VideoDTO dto) {
		VideoEntity entity = new VideoEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setStartTime("20210101");
		entity.setFundCode(dto.getFundCode());
		entity.setPageNumber(dto.getPageNumber());
		entity.setVideoPlayNumber(dto.getVideoPlayNumber());
		entity.setVideoUrl(dto.getVideoUrl());
		entity.setToogle(dto.isToogle());
		entity.setContent(dto.getContent());
		entity.setFundType(dto.getFundType());
		return entity;
	}
}
