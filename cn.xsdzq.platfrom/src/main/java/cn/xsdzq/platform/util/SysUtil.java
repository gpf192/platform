package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.model.lcj.ParamDTO;

public class SysUtil {
	public static ParamEntity convertParamEntityByDTO(ParamDTO dto) {
		ParamEntity entity = new ParamEntity();
		entity.setId(dto.getId());
		entity.setCode(dto.getCode());
		entity.setValue(dto.getValue());
		entity.setTitle(dto.getTitle());
		
		return entity;
	}

	public static ParamDTO convertParamDTOByEntity(ParamEntity entity) {
		ParamDTO dto = new ParamDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setValue(entity.getValue());
		dto.setTitle(entity.getTitle());
		
		return dto;
	}
}
