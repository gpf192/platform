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
	public static String hideString(String pw) {
		String b = "";
		if(pw.length()>10) {
			 b = pw.substring(0, 4)+"********"+pw.substring( pw.length()-4,pw.length());

		}else if(pw.length()<=4) {
			 b = "********";

		}else {
			b = pw.substring(0, 2)+"********"+pw.substring( pw.length()-2,pw.length());
		}
		
		return b;
	}
}
