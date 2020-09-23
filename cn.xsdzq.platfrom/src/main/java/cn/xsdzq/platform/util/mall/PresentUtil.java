package cn.xsdzq.platform.util.mall;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.entity.mall.PresentRecordEntity;
import cn.xsdzq.platform.entity.mall.PresentResultEntity;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
import cn.xsdzq.platform.model.mall.PresentCategoryDTO;
import cn.xsdzq.platform.model.mall.PresentDTO;
import cn.xsdzq.platform.model.mall.PresentRecodDTO;
import cn.xsdzq.platform.util.DateUtil;

public class PresentUtil {
	
	public static PresentCategoryDTO convertPresentCategoryDTOByEntity(PresentCategoryEntity entity) {
			
		PresentCategoryDTO dto = new PresentCategoryDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCode( entity.getCode());
		dto.setFlag(entity.getFlag());
		dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));
		return dto;
		}
	public static PresentCategoryEntity convertPresentCategoryEntityByDto(PresentCategoryDTO dto) {
		
		PresentCategoryEntity entity = new PresentCategoryEntity();
		if(dto.getIsNew() == 1 ) {
			entity.setId(dto.getId());
		}
		
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setFlag(dto.getFlag());
		return entity;
	}
	public static PresentEntity  convertPresentEntityByDto(PresentDTO dto) {
		PresentEntity entity = new PresentEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setImage(dto.getImage());
		entity.setBigImage(dto.getBigImage());
		
		entity.setHot(dto.isHot());
		entity.setCategoryId(dto.getCategoryId());
		entity.setDescription(dto.getDescription());
		entity.setFaceValue(dto.getFaceValue());
		entity.setValue(dto.getValue());
		entity.setStatus(dto.getStatus());
		
		entity.setTip(dto.getTip());
		entity.setExplain(dto.getExplain());
		return entity;
	}
	
	public static PresentDTO convertPresentDTOByEntity(PresentEntity entity) {
		
		PresentDTO dto = new PresentDTO();
		dto.setId(entity.getId());
		dto.setCategoryName(entity.getPresentCategory().getName());
		dto.setCategoryId(entity.getCategoryId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());

		dto.setImage(entity.getImage());
		dto.setBigImage(entity.getBigImage());
		
		dto.setHot(entity.isHot());		
		dto.setFaceValue(entity.getFaceValue());
		dto.setValue(entity.getValue());
		dto.setDescription(entity.getDescription());
		dto.setTip(entity.getTip());
		dto.setExplain(entity.getExplain());
		dto.setStatus(entity.getStatus());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public static PresentCardDTO convertPresentCardDTOByEntity(PresentCardEntity entity) {
		
		PresentCardDTO dto = new PresentCardDTO();
		dto.setId(entity.getId());
		dto.setCardId(entity.getCardId());
		dto.setPresentId(entity.getPresentId());
		dto.setPresentName(entity.getPresent().getName());
		dto.setPassword(entity.getPassword());
		dto.setCardStatus(entity.getCardStatus());
		dto.setConvertStatus(entity.getConvertStatus());
		dto.setCreateDate(DateUtil.DateToString(entity.getCreateDate()));
		return dto;
	}
	
	public static PresentCardEntity convertPresentCardEntityByDTO(PresentCardDTO dto) {
		
		PresentCardEntity entity = new PresentCardEntity();	
		entity.setId(dto.getId());
		entity.setCardId(dto.getCardId());
		entity.setPassword(dto.getPassword());
		entity.setCardStatus(1);//默认上架
		entity.setPresentId(dto.getPresentId());
		entity.setConvertStatus(0);//默认未兑换
		return entity;
	}
	
	//兑换记录
	public static PresentRecodDTO  convertDTOByPresentRecordEntity(PresentRecordEntity entity) {
		 
			
		PresentRecodDTO dto = new PresentRecodDTO();
		dto.setClientName(entity.getMallUserEntity().getClientName());
		dto.setClientId(entity.getMallUserEntity().getClientId());
		dto.setDepartmentName(entity.getMallUserEntity().getDepartmentName());
		dto.setMobile(entity.getMallUserEntity().getMobile());
		dto.setPresentName(entity.getPresentCardEntity().getPresent().getName());
		
		dto.setCardId(entity.getPresentCardEntity().getCardId());
		dto.setPassword(entity.getPresentCardEntity().getPassword());
		dto.setPrice(entity.getPrice());
		dto.setIntegralNum(entity.getIntegralNumber());
		dto.setRecordTime(DateUtil.DateToString(entity.getRecordTime()));
		return dto;
	}
	
}
