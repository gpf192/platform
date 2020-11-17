package cn.xsdzq.platform.util.mall;

import java.util.List;

import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditProductViewEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.model.mall.CRMCreditApiMsgDTO;
import cn.xsdzq.platform.model.mall.CRMCreditProductViewDTO;
import cn.xsdzq.platform.model.mall.CRMCreditRecordDTO;
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
		dto.setMobile(entity.getImportMobile());
		dto.setDepartmentDesc(entity.getMallUserEntity().getDepartmentName());
		dto.setDepartmentCode(entity.getMallUserEntity().getDepartmentCode());
		dto.setSerialNum(entity.getSerialNum());
		if(entity.isType()) {
			dto.setCategoryName(entity.getItem());
			dto.setCategoryCode(entity.getItemCode());
			dto.setNum("+"+entity.getIntegralNumber());
			dto.setEndDate(entity.getEndDate()+"");
		}else {
			dto.setCategoryName(entity.getReason()+":"+entity.getItem());
			if("11".equals(entity.getReasonCode())) {
				dto.setCategoryCode(entity.getItemCode());
			}else {
				dto.setCategoryCode(entity.getReasonCode());	
			}
			
			dto.setNum("-"+entity.getIntegralNumber());
			dto.setEndDate("");
		}
		
		dto.setImportItem(entity.getImportItem());
		dto.setBeginDate(entity.getBeginDate());
		
		dto.setRecordTime(DateUtil.DateToString(entity.getRecordTime()));

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
		dto.setEndDate(entity.getEndDate()+"");
		return dto;
	}
	
	public static CreditImportTempEntity toCreditImportTempEntity(List<Object> lo) {
		CreditImportTempEntity vo = new CreditImportTempEntity();
		
		   vo.setClientName(String.valueOf(lo.get(0)).replaceAll(" ", "")); 
		   vo.setClientId(String.valueOf(lo.get(1)).replaceAll(" ", "")); 
		   vo.setMobile(String.valueOf(lo.get(2)).replaceAll(" ", "")); 
		   vo.setDepartmentDesc(String.valueOf(lo.get(3)).replaceAll(" ", ""));
		   vo.setDepartmentCode(String.valueOf(lo.get(4)).replaceAll(" ", ""));
		   vo.setCategoryName(String.valueOf(lo.get(5)).replaceAll(" ", ""));
		   vo.setCategoryCode(String.valueOf(lo.get(6)).replaceAll(" ", ""));
		   vo.setNum(String.valueOf(lo.get(7)).replaceAll(" ", ""));
		   vo.setBeginDate(String.valueOf(lo.get(8)).replaceAll(" ", ""));
		   //vo.setEndDate(String.valueOf(lo.get(9)).replaceAll(" ", ""));
		   
		return vo;
	}
	//导入表格的临时类转换为正式实体类,  不用此方法 
	public static CRMCreditRecordDTO convertCRMCreditDTOByEntity(CRMCreditRecordEntity entity) {
		CRMCreditRecordDTO dto = new CRMCreditRecordDTO();
		dto.setClientId(entity.getClientId());
		dto.setClientName(entity.getClientName());
		dto.setSerialNum(entity.getSerialNum());
		
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setDepartmentDesc(entity.getDepartmentDesc());
		dto.setMobile(entity.getMobile());
		
		dto.setItemCode(entity.getItemCode());
		dto.setItemName(entity.getItemName());
		dto.setNum(entity.getNum());
		
		dto.setBeginDate(entity.getBeginDate());
		dto.setEndDate(entity.getEndDate());
		return dto;
	}
	
	public static CRMCreditApiMsgDTO convertCRMCreditMsgDTOByEntity(CRMCreditApiErrorMsgEntity entity) {
		CRMCreditApiMsgDTO dto = new CRMCreditApiMsgDTO();
		
		dto.setId(entity.getId());
		dto.setSerialNum(entity.getSerialNum());
		dto.setMsg(entity.getMsg());
		dto.setRecordTime(DateUtil.DateToString(entity.getRecordTime()));
		return dto;
	}

	public static CRMCreditProductViewDTO convertCRMCreditProductDTOByEntity(CRMCreditProductViewEntity entity) {
		CRMCreditProductViewDTO dto = new CRMCreditProductViewDTO();
		
		dto.setProductCode(entity.getProductCode());
		dto.setProductName(entity.getProductName());
		dto.setProductType(entity.getProductType());
		dto.setFundType(entity.getFundType());
		dto.setRiskType(entity.getRiskType());
		dto.setPurchaseRate(entity.getPurchaseRate());
		
		return dto;
	}
}
