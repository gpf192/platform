package cn.xsdzq.platform.util;

import java.util.HashMap;
import java.util.Map;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;
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

	public static UserOrderDTO convertUserOrderDTOByEntity(ThxOrderEntity entity) {		
		UserOrderDTO dto = new UserOrderDTO();
		dto.setId(entity.getId());
		dto.setOrderId(entity.getOrderId());
		dto.setOrderStatus(entity.getOrderStatus());
		dto.setUserId(entity.getUserId());
		try {
			dto.setUsername(AES.Decrypt(entity.getUsername()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dto.setGoodsType(entity.getGoodsType());
		dto.setGoodsId(entity.getGoodsId());
		dto.setCertificate(entity.getCertificate());
		dto.setExpiredate(entity.getExpiredate());
		dto.setProductName(entity.getProductName());
		dto.setGoodsRisk(entity.getGoodsRisk());
		dto.setEvaluationResult(entity.getEvaluationResult());
		dto.setBrokerName(entity.getBrokerName());
		dto.setSalesName(entity.getSalesName());
		try {
			dto.setTgName(AES.Decrypt(entity.getTgName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dto.setTgCertification(AES.Decrypt(entity.getTgCertification()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setServiceCycle(entity.getServiceCycle());
		dto.setAmount(entity.getAmount());
		dto.setBookTime(entity.getBookTime());
		dto.setBookOrigin(entity.getBookOrigin());
		dto.setAssignTime(entity.getAssignTime());
		dto.setAgreements(entity.getAgreements());
		dto.setRiskRevelation(entity.getRiskRevelation());
		dto.setMatchInstruction(entity.getMatchInstruction());
		try {
			dto.setAddress(AES.Decrypt(entity.getAddress()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setOccupation(entity.getOccupation());
		dto.setMobile(entity.getMobile());
		dto.setEvaluationTime(entity.getEvaluationTime());
		return dto;
	}
	
	public static ThxOrderEntity changeMapToEntity(Map<String, String> map) {
		ThxOrderEntity t = new ThxOrderEntity();
		t.setOrderId(map.get("order_id"));
		t.setUserId(map.get("userid"));
		t.setGoodsType(map.get("goods_type"));
		t.setGoodsId(map.get("goods_id"));
		t.setCertificate(map.get("certificate"));
		t.setProductName(map.get("product_name"));
		t.setGoodsRisk(map.get("goods_risk"));
		
		t.setBrokerName(map.get("broker_name"));
		t.setSalesName(map.get("sales_name"));
		t.setTgName(map.get("tg_name"));
		t.setServiceCycle(map.get("service_cycle"));
		t.setAmount(map.get("amount"));
		t.setBookTime(map.get("book_time"));
		
		t.setBookOrigin(map.get("book_origin"));
		t.setAssignTime(map.get("assign_time"));
		t.setAgreements(map.get("agreements"));
		t.setRiskRevelation(map.get("risk_revelation"));

		t.setTgCertification(map.get("tg_certification"));
		t.setServiceCycleStart(map.get("service_cycle_start"));
		t.setServiceCycleEnd(map.get("service_cycle_end"));
		
		t.setOrderStatus(map.get("order_status"));
		t.setUsername(map.get("username"));
		t.setMobile(map.get("mobile"));
		
		t.setAddress(map.get("address"));
		t.setExpiredate(map.get("expiredate"));
		t.setEvaluationResult(map.get("evaluation_result"));
		t.setEvaluationTime(map.get("evaluation_time"));
		
		t.setEvaluationAnswer(map.get("evaluation_answer"));
		t.setOccupation(map.get("occupation"));
		t.setMatchInstruction(map.get("match_instruction"));
		
		return t;
	}
}
