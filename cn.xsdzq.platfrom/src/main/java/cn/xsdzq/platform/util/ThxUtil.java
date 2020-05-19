package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.thx.UserOrderEntity;
import cn.xsdzq.platform.entity.thx.UserRiskEntity;
import cn.xsdzq.platform.model.thx.UserOrderDTO;
import cn.xsdzq.platform.model.thx.UserRiskDTO;

public class ThxUtil {
	public static UserRiskDTO convertDTOByEntity(UserRiskEntity entity) {		
		UserRiskDTO dto = new UserRiskDTO();
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		dto.setUsername(entity.getUsername());
		dto.setCertificate(entity.getCertificate());
		dto.setMobile(entity.getMobile());
		dto.setEvaluationAnswer(entity.getEvaluationAnswer());
		dto.setEvaluationResult(entity.getEvaluationResult());
		dto.setEvaluationTime(entity.getEvaluationTime());
		
		return dto;
	}

	public static UserOrderDTO convertUserOrderDTOByEntity(UserOrderEntity entity) {		
		UserOrderDTO dto = new UserOrderDTO();
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		dto.setGoodsType(entity.getGoodsType());
		dto.setGoodsId(entity.getGoodsId());
		dto.setCertificate(entity.getCertificate());;
		dto.setProductName(entity.getProductName());
		dto.setGoodsRisk(entity.getGoodsRisk());
		dto.setEvaluationResult(entity.getEvaluationResult());
		dto.setBrokerName(entity.getBrokerName());
		dto.setSalesName(entity.getSalesName());
		dto.setTgName(entity.getTgName());
		dto.setServiceCycle(entity.getServiceCycle());
		dto.setAmount(entity.getAmount());
		dto.setBookTime(entity.getBookTime());
		dto.setBookOrigin(entity.getBookOrigin());
		dto.setAssignTime(entity.getAssignTime());
		dto.setAgreements(entity.getAgreements());
		dto.setRiskRevelation(entity.getRiskRevelation());
		dto.setMatchInstruction(entity.getMatchInstruction());
		
		
		return dto;
	}
}
