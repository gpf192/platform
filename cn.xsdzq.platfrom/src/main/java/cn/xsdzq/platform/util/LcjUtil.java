package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.lcj.PrizeDTO;
import cn.xsdzq.platform.model.lcj.PrizeRecordDTO;

public class LcjUtil {
	public static PrizeDTO convertPrizeDTOByPrize(PrizeEntity entity) {		
		PrizeDTO dto = new PrizeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		dto.setPice(entity.getPrice());
		dto.setRate(entity.getRate());
		dto.setAmount(entity.getAmount());
		/*try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return dto;
	}
	public static PrizeEntity convertEntityByPrizeDTO(PrizeDTO dto) {
		PrizeEntity entity = new PrizeEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPice());
		entity.setAmount(dto.getAmount());

		return entity;
	}
	
	public static PrizeRecordDTO convertPrizeRecordDTOByPrizeRecord(PrizeRecordEntity entity) {		
		PrizeRecordDTO dto = new PrizeRecordDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		
		dto.setAccount(entity.getAccount());
		dto.setPrizeName(entity.getPrizeName());
		dto.setCreatetime(entity.getCreatetime());
		
		return dto;
	}
}
