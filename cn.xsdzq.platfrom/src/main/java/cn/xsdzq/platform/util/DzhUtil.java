package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.DzhActivityEntity;
import cn.xsdzq.platform.entity.DzhggEntity;
import cn.xsdzq.platform.model.DzhActivityDTO;
import cn.xsdzq.platform.model.DzhDTO;

public class DzhUtil {
	public static DzhDTO convertDzhggDTOByEntity(DzhggEntity entity) {		
		DzhDTO dto = new DzhDTO();
		dto.setId(entity.getId());
		dto.setActivity(entity.getActivity());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		try {
			dto.setRecordtime(DateUtil.DateToString(entity.getRecordtime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	public static DzhActivityDTO convertDzhActvityDTOByEntity(DzhActivityEntity entity) {		
		DzhActivityDTO dto = new DzhActivityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
			
		return dto;
	}
}
