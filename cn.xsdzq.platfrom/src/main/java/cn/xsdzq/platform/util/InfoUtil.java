package cn.xsdzq.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.model.InfoDTO;

public class InfoUtil {

	public static InfoEntity convertInfoByInfoDTO(InfoDTO dto) {
		InfoEntity infoEntity = new InfoEntity();
		infoEntity.setId(dto.getId());
		infoEntity.setTitle(dto.getTitle());
		infoEntity.setCategoryId(dto.getCategoryId());
		infoEntity.setLabel(dto.getLabel());
		infoEntity.setContent(dto.getContent());
		infoEntity.setExp(dto.getExp());
		infoEntity.setCheckedResult(dto.getFlag());
		infoEntity.setWeight(dto.getWeight());
		infoEntity.setPageView(dto.getPageView());
		infoEntity.setCommonFlag(dto.getCommonFlag());
		return infoEntity;
	}

	public static InfoDTO convertInfoDTOByInfo(InfoEntity infoEntity) {
		InfoDTO dto = new InfoDTO();
		dto.setId(infoEntity.getId());
		dto.setTitle(infoEntity.getTitle());
		dto.setCategoryId(infoEntity.getCategoryId());
		dto.setLabel(infoEntity.getLabel());
		dto.setContent(infoEntity.getContent());
		dto.setExp(infoEntity.getExp());
		dto.setChecked_result(infoEntity.getCheckedResult());
		dto.setWeight(infoEntity.getWeight());
		dto.setPageView(infoEntity.getPageView());
		dto.setCreatedBy( infoEntity.getCreatedBy());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dto.setModifytime(sdf.format(infoEntity.getModifytime())); 
		CategoryEntity  categoryEntity = infoEntity.getCategoryEntity();
		dto.setCategoryTitle(categoryEntity.getTitle());
		dto.setCommonFlag(infoEntity.getCommonFlag());
		return dto;
	}

}
