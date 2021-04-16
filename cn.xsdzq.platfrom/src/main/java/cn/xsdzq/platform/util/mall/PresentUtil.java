package cn.xsdzq.platform.util.mall;

import java.util.ArrayList;
import java.util.List;

import cn.xsdzq.platform.entity.mall.CardImportTempEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentCategoryEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.entity.mall.PresentRecordEntity;
import cn.xsdzq.platform.model.mall.CardImportTempDTO;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
import cn.xsdzq.platform.model.mall.PresentCategoryDTO;
import cn.xsdzq.platform.model.mall.PresentDTO;
import cn.xsdzq.platform.model.mall.PresentRecodDTO;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.SysUtil;

public class PresentUtil {
	
	public static PresentCategoryDTO convertPresentCategoryDTOByEntity(PresentCategoryEntity entity) {
			
		PresentCategoryDTO dto = new PresentCategoryDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCode( entity.getCode());
		dto.setFlag(entity.getFlag());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		entity.setIsHot(dto.getIsHot());
		entity.setCategoryId(dto.getCategoryId());
		entity.setDescription(dto.getDescription());
		entity.setFaceValue(dto.getFaceValue());
		entity.setValue(dto.getValue());
		entity.setStatus(dto.getStatus());
		
		entity.setTip(dto.getTip());
		entity.setExplain(dto.getExplain());
		if(dto.getNewFlag()==0) {
			entity.setCreatetime(DateUtil.stringToDate1(dto.getCreatetime()));

		}
		entity.setAttention(dto.getAttention());
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
		dto.setIsHot(entity.getIsHot());		
		dto.setFaceValue(entity.getFaceValue());
		dto.setValue(entity.getValue());
		dto.setDescription(entity.getDescription());
		dto.setTip(entity.getTip());
		dto.setExplain(entity.getExplain());
		dto.setStatus(entity.getStatus());
		dto.setAttention(entity.getAttention());
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
		if(entity.getPassword()==null) {
			dto.setPassword(entity.getPassword());
		}else {
			dto.setPassword(SysUtil.hideString(entity.getPassword()));//密码隐藏
		}

		dto.setCardStatus(entity.getCardStatus());
		dto.setConvertStatus(entity.getConvertStatus());
		dto.setCreateDate(DateUtil.DateToString(entity.getCreateDate()));
		if(entity.getExpiryTime()==20991231) {
			dto.setExpiryTime("无");
		}else {
			dto.setExpiryTime(DateUtil.stringToDateAndSeconds2(entity.getExpiryTime()));
		}
	
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
		entity.setExpiryTime(Integer.parseInt(dto.getExpiryTime()));
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
		if(entity.getPresentCardEntity().getPassword()==null) {
			dto.setPassword(entity.getPresentCardEntity().getPassword());
		}else {
			dto.setPassword(SysUtil.hideString(entity.getPresentCardEntity().getPassword()));
		}
		
		dto.setPrice(entity.getPrice());
		dto.setIntegralNum(entity.getIntegralNumber());
		dto.setRecordTime(DateUtil.DateToString(entity.getRecordTime()));
		return dto;
	}
	public static CardImportTempDTO convertCardImportTempDTOByEntity(CardImportTempEntity entity) {
		CardImportTempDTO dto = new CardImportTempDTO();
		dto.setCardId(entity.getCardId());
		dto.setPassword(entity.getPassword());
		dto.setPresentCode(entity.getPresentCode());
		dto.setCardStatus(1);
		dto.setConvertStatus(0);
		String date = entity.getExpiryTime();
		if(!"无".equals(date)) {
			String s = date.substring(0, 4)+"-"+date.substring(4, 6)
			+"-"+date.substring(6, 8);
			dto.setExpiryTime(s+" 00:00:00");
		}else {
			dto.setExpiryTime(date);
		}
		
		return dto;
	}
	
	public static CardImportTempEntity toCardImportTempEntity(List<Object> lo) {
		CardImportTempEntity vo = new CardImportTempEntity();
		
		   vo.setCardId(String.valueOf(lo.get(0)).replaceAll(" ", "")); 
		   vo.setPassword(String.valueOf(lo.get(1)).replaceAll(" ", "")); 
		   vo.setPresentCode(String.valueOf(lo.get(2)).replaceAll(" ", ""));
		   vo.setExpiryTime(String.valueOf(lo.get(3)).replaceAll(" ", ""));
		   vo.setCardStatus(1);
		   vo.setConvertStatus(0);
		  
		return vo;
	}
	
	public static PresentCardEntity cardTempEntityToCardEntity(CardImportTempEntity temp) {
		PresentCardEntity card = new PresentCardEntity();
		card.setCardId(temp.getCardId());
		card.setPassword(temp.getPassword());
		card.setCardStatus(1);
		card.setConvertStatus(0);
		if("无".equals(temp.getExpiryTime())) {
			card.setExpiryTime(20991231);
		}else{
			card.setExpiryTime(Integer.parseInt(temp.getExpiryTime()));
		}
		
		return card;
	}
	public static boolean  isRepeat(List<CardImportTempEntity> temps){
		List<CardImportTempEntity> a =  new ArrayList<CardImportTempEntity>();
		for (CardImportTempEntity entity : temps) {
			a.add(entity);
		}
		
		for (int i = 0; i < temps.size(); i++) {  
			int num = 0;
			for (int j = 0; j < a.size(); j++) {    
				// 判断两个集合中数据是否相等(查重)     
				if (temps.get(i).getCardId().equals(a.get(j).getCardId())) {      
					num = num+1;          
					}   
				}
			if(num>1) {
				return true;
			}
		}
		return false;						
	}		

}
