package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.entity.DzhggEntity;
import cn.xsdzq.platform.model.DzhDTO;
import cn.xsdzq.platform.model.KCDTO;

public class KCUtil {
	public static KCDTO convertInfoDTOByInfo(CustomerMobileEntity entity) {		
		KCDTO dto = new KCDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUserEntity().getClientName());
		dto.setClientId(entity.getUserEntity().getClientId());
		dto.setPhone(entity.getUserEntity().getMobile());
		dto.setPageEventId(entity.getPageEventId());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getRecordTime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	
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
}
