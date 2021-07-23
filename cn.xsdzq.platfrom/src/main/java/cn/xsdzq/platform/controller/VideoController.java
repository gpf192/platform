package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.VideoEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.VideoDTO;
import cn.xsdzq.platform.model.VideoId;
import cn.xsdzq.platform.service.VideoService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.VideoUtil;

@RestController
@RequestMapping(value = "/video")
public class VideoController {
	Logger logger = LogManager.getLogger(VideoController.class.getName());

	@Autowired
	private VideoService videoService;

	
	//cms
	
	@GetMapping(value = "/getVideoList")
	@ResponseBody
	public Map<String, Object> getVideoList(HttpServletRequest request,@RequestParam int fundType,@RequestParam int pageNumber,@RequestParam int pageSize) {
		List<VideoEntity> entities  = videoService.findAll(fundType ,pageNumber, pageSize);
		int sum = videoService.coutAll(fundType);
		
		List<VideoDTO> dtos = new ArrayList<VideoDTO>();
		for (VideoEntity entity : entities) {
			VideoDTO dto = VideoUtil.convertVideoDTOByEntityToMain(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);

	}
	
	@RequestMapping(value = "/addVideo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addVideo(@Validated @RequestBody VideoDTO videoDTO) {

		logger.info(videoDTO.toString());
		VideoEntity entity = VideoUtil.convertVideoEntityByDTO(videoDTO);
		
		videoService.addVideo(entity);
		return GsonUtil.buildMap(0, "ok", null);

	}

	@RequestMapping(value = "/deleteVideo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteVideo(@RequestBody VideoDTO dto) {

		logger.info(dto.toString());
		//UserEntity userEntity = UserUtil.convertUserEntityByUserDTO(userDTO);
		videoService.deleteVideo(dto.getId());
		return GsonUtil.buildMap(0, "ok", null);

	}

}
