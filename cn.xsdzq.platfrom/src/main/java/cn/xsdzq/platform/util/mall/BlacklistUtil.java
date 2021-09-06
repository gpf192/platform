package cn.xsdzq.platform.util.mall;
import java.util.ArrayList;
import java.util.List;

import cn.xsdzq.platform.entity.mall.CardImportTempEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.UserBlackListEntity;
import cn.xsdzq.platform.entity.mall.UserBlackListImportEntity;
import cn.xsdzq.platform.model.mall.CardImportTempDTO;
import cn.xsdzq.platform.model.mall.UserBlacklistDTO;
import cn.xsdzq.platform.model.mall.UserBlacklistImortDTO;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.UserManageUtil;

public class BlacklistUtil {
	public static UserBlacklistDTO convertDTOByEntity(UserBlackListEntity entity) {
		UserBlacklistDTO dto = new UserBlacklistDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getClientId());
		dto.setClientName(entity.getClientName());
		dto.setDepartmentDesc(entity.getDepartmentDesc());
		dto.setCreateDate(DateUtil.DateToString(entity.getCreateDate()));	
		
		return dto;
	}
	public static UserBlackListEntity convertEntityByDTO(UserBlacklistDTO dto) {
		UserBlackListEntity entity = new UserBlackListEntity();
		entity.setId(dto.getId());
		entity.setClientId(dto.getClientId());
		entity.setClientName(dto.getClientName());
		entity.setDepartmentDesc(dto.getDepartmentDesc());

		return entity;
	}
	
	public static UserBlackListImportEntity toUserBlacklistTempEntity(List<Object> lo) {
		UserBlackListImportEntity vo = new UserBlackListImportEntity();
		
		   vo.setClientId(String.valueOf(lo.get(0)).replaceAll(" ", "")); 
		   try {
			vo.setClientName(String.valueOf(lo.get(1)).replaceAll(" ", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vo.setClientName("");
		} 
		   try {
			vo.setDepartmentDesc(String.valueOf(lo.get(2)).replaceAll(" ", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vo.setDepartmentDesc("");
		} 
		   
		return vo;
	}
	
	public static UserBlacklistImortDTO convertUserBlacklistImortDTOByEntity(UserBlackListImportEntity entity) {
		UserBlacklistImortDTO dto = new UserBlacklistImortDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getClientId());
		dto.setClientName(entity.getClientName());
		dto.setDepartmentDesc(entity.getDepartmentDesc());
		return dto;
	}
	
	public static boolean  isRepeat(List<UserBlackListImportEntity> temps){
		List<UserBlackListImportEntity> a =  new ArrayList<UserBlackListImportEntity>();
		for (UserBlackListImportEntity entity : temps) {
			a.add(entity);
		}
		
		for (int i = 0; i < temps.size(); i++) {  
			int num = 0;
			for (int j = 0; j < a.size(); j++) {    
				// 判断两个集合中数据是否相等(查重)     
				if (temps.get(i).getClientId().equals(a.get(j).getClientId())) {      
					num = num+1;          
					}   
				}
			if(num>1) {
				return true;
			}
		}
		return false;						
	}	
	
	public static UserBlackListEntity TempEntityToCardEntity(UserBlackListImportEntity temp) {
		UserBlackListEntity entity = new UserBlackListEntity();
		entity.setClientId(temp.getClientId());
		entity.setClientName(temp.getClientName());
		entity.setDepartmentDesc(temp.getDepartmentDesc());
		entity.setCreatedBy(UserManageUtil.getUser().getUsername());
		return entity;
	}
}
