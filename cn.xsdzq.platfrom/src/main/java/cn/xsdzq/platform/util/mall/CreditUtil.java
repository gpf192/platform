package cn.xsdzq.platform.util.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.model.mall.CreditDTO;
import cn.xsdzq.platform.model.mall.CreditImportRecordDTO;
import cn.xsdzq.platform.model.mall.CreditImportTempDTO;
import cn.xsdzq.platform.model.mall.CreditUserTotalDTO;
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
	
	public static CreditUserTotalDTO convertCreditUserTotalDTOByEntity(MallUserInfoEntity entity) {
		CreditUserTotalDTO dto = new CreditUserTotalDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getMallUserEntity().getClientId());
		dto.setClientName(entity.getMallUserEntity().getClientName());
		dto.setDepartmentCode(entity.getMallUserEntity().getDepartmentCode());
		dto.setDepartmentDesc(entity.getMallUserEntity().getDepartmentName());
		dto.setMobile(entity.getMallUserEntity().getMobile());
		dto.setTotal(entity.getCreditScore());
		
		return dto;
	}
	
	public static CreditImportRecordDTO convertCreditImportRecordDTOByEntity(CreditRecordEntity entity) {
		CreditImportRecordDTO dto = new CreditImportRecordDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getMallUserEntity().getClientId());
		dto.setClientName(entity.getMallUserEntity().getClientName());
		dto.setMobile(entity.getMallUserEntity().getMobile());
		dto.setDepartmentDesc(entity.getMallUserEntity().getDepartmentName());
		dto.setDepartmentCode(entity.getMallUserEntity().getDepartmentCode());
		dto.setCategoryName(entity.getItem());
		dto.setCategoryCode(entity.getItemCode());
		dto.setNum(entity.getIntegralNumber());
		dto.setBeginDate(entity.getBeginDate());
		dto.setEndDate(entity.getEndDate());
		return dto;
	}
	
	public static CreditImportTempDTO convertCreditImportTempDTOByEntity(CreditImportTempEntity entity) {
		CreditImportTempDTO dto = new CreditImportTempDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getClientId());
		dto.setClientName(entity.getClientName());
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setDepartmentDesc(entity.getDepartmentDesc());
		dto.setMobile(entity.getMobile());
		dto.setCategoryName(entity.getCategoryName());
		dto.setCategoryCode(entity.getCategoryCode());
		dto.setNum(entity.getNum());
		dto.setBeginDate(entity.getBeginDate());
		dto.setEndDate(entity.getEndDate());
		return dto;
	}
	
	public static CreditImportTempEntity toCreditImportTempEntity(List<Object> lo) {
		CreditImportTempEntity vo = new CreditImportTempEntity();
		
		   vo.setClientName(String.valueOf(lo.get(0))); 
		   vo.setClientId(String.valueOf(lo.get(1))); 
		   vo.setMobile(String.valueOf(lo.get(2))); 
		   vo.setDepartmentDesc(String.valueOf(lo.get(3)));
		   vo.setDepartmentCode(String.valueOf(lo.get(4)));
		   vo.setCategoryName(String.valueOf(lo.get(5)));
		   vo.setCategoryCode(String.valueOf(lo.get(6)));
		   vo.setNum(String.valueOf(lo.get(7)));
		   vo.setBeginDate(String.valueOf(lo.get(8)));
		   vo.setEndDate(String.valueOf(lo.get(9)));
		   
		return vo;
	}
	//导入表格的临时类转换为正式实体类,  不用此方法 
	public static CreditRecordEntity changeTempToRecord(CreditImportTempEntity temp) {
		CreditRecordEntity entity = new CreditRecordEntity();
	//	entity.setClientId(temp.getClientId());
		//entity.setClientName(temp.getClientName());
		//entity.setDepartmentCode(temp.getDepartmentCode());
		//entity.setDepartmentDesc(temp.getDepartmentDesc());
		//entity.setMobile(temp.getMobile());
		//entity.setCategoryName(temp.getCategoryName());
		//entity.setCategoryCode(temp.getCategoryCode());
		//entity.setNum(temp.getNum());
		entity.setBeginDate(temp.getBeginDate());
		entity.setEndDate(temp.getEndDate());
		return entity;
	}

}
