package cn.xsdzq.platform.util.mall;

import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.mall.PresentDTO;

public class PresentUtil {
	public static PresentDTO convertPresentDTOByEntity(PresentEntity entity) {
		
		PresentDTO dto = new PresentDTO();
		dto.setId(entity.getId());
		dto.setCategoryName(entity.getPresentCategory().getName());
		dto.setName(entity.getName());
		dto.setFaceValue(entity.getFaceValue());
		dto.setValue(entity.getValue());
		dto.setDescription(entity.getDescription());
		dto.setStoreNumber(entity.getStoreNumber());
		dto.setConvertNumber(entity.getConvertNumber());
		dto.setStoreUnused(entity.getStoreUnused());;
		dto.setStatus(entity.getStatus());
		return dto;
	}
}
