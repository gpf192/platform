package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.model.KCDTO;

public class KCUtil {
	public static KCDTO convertInfoDTOByInfo(CustomerMobileEntity entity) {		
		KCDTO dto = new KCDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getUserEntity().getClientName());
		dto.setClientId(entity.getUserEntity().getClientId());
		dto.setPhone(entity.getUserEntity().getMobile());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getRecordTime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
}
