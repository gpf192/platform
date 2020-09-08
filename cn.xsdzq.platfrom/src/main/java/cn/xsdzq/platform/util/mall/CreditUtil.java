package cn.xsdzq.platform.util.mall;

import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.model.mall.CreditDTO;
import cn.xsdzq.platform.util.DateUtil;

public class CreditUtil {
	public static CreditDTO convertCreditDTOByEntity(CreditEntity entity) {
			
		CreditDTO dto = new CreditDTO();		
			dto.setId(entity.getId());
			dto.setCategoryName(entity.getCategoryName());
			dto.setCategoryCode(entity.getCategoryCode());
			dto.setFrontName(entity.getFrontName());
			dto.setIntegralValue(entity.getIntegralValue());
			dto.setFlag(entity.getFlag());
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));
			return dto;
		}
	
	public static CreditEntity convertCreditEntityByDTO(CreditDTO dto) {
		
		CreditEntity entity = new CreditEntity();		
		entity.setId(dto.getId());
		entity.setCategoryName(dto.getCategoryName());
		entity.setCategoryCode(dto.getCategoryCode());
		entity.setFrontName(dto.getFrontName());
		entity.setIntegralValue(dto.getIntegralValue());
		entity.setFlag(dto.getFlag());
		return entity;
		}
}
