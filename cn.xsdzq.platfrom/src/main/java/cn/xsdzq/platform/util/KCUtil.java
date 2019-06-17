package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.model.KCDTO;

public class KCUtil {
	public static KCDTO convertInfoDTOByInfo(CustomerMobileEntity entity) {		
		KCDTO dto = new KCDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
}
