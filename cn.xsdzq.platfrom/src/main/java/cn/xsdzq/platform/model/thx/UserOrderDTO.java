package cn.xsdzq.platform.model.thx;

import java.io.Serializable;

import javax.persistence.Column;

public class UserOrderDTO implements Serializable{
private long id;//主键
	
	private String userId;//用户ID
	
	private String goodsType;//产类型品
	
	private String goodsId;//商品ID
	
	private String certificate;//用户身份证号码
	
	private String productName;//投顾产品名称
	
	private String goodsRisk;//产品风险等级
	
	private String evaluationResult;//风险测评结果
	
	private String brokerName;//所属券商名称
	
	private String salesName;//所属营业部名称
	
	private String tgName;//所属投顾姓名
	
	private String serviceCycle;//服务周期
	
	private String amount;//订阅金额
	
	private String bookTime;//订阅时间
	
	private String bookOrigin;//订阅来源,1.手抄android2.手抄ios3.微信客户端4.股市教练安卓5.股市教练ios6.PC网页
	
	private String assignTime;//获得名额时间
	
	private String agreements;//签署的所有协议
	
	private String riskRevelation;//风险揭示书
	
	private String matchInstruction;//适当性匹配说明,1：匹配 0：不匹配

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getGoodsRisk() {
		return goodsRisk;
	}

	public void setGoodsRisk(String goodsRisk) {
		this.goodsRisk = goodsRisk;
	}

	public String getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(String evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getTgName() {
		return tgName;
	}

	public void setTgName(String tgName) {
		this.tgName = tgName;
	}

	public String getServiceCycle() {
		return serviceCycle;
	}

	public void setServiceCycle(String serviceCycle) {
		this.serviceCycle = serviceCycle;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getBookOrigin() {
		return bookOrigin;
	}

	public void setBookOrigin(String bookOrigin) {
		this.bookOrigin = bookOrigin;
	}

	

	public String getAgreements() {
		return agreements;
	}

	public void setAgreements(String agreements) {
		this.agreements = agreements;
	}

	

	public String getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}

	public String getRiskRevelation() {
		return riskRevelation;
	}

	public void setRiskRevelation(String riskRevelation) {
		this.riskRevelation = riskRevelation;
	}

	public String getMatchInstruction() {
		return matchInstruction;
	}

	public void setMatchInstruction(String matchInstruction) {
		this.matchInstruction = matchInstruction;
	}

	@Override
	public String toString() {
		return "UserOrderDTO [id=" + id + ", userId=" + userId + ", goodsType=" + goodsType + ", goodsId=" + goodsId
				+ ", certificate=" + certificate + ", productName=" + productName + ", goodsRisk=" + goodsRisk
				+ ", evaluationResult=" + evaluationResult + ", brokerName=" + brokerName + ", salesName=" + salesName
				+ ", tgName=" + tgName + ", serviceCycle=" + serviceCycle + ", amount=" + amount + ", book_time="
				+ bookTime + ", bookOrigin=" + bookOrigin + ", assign_time=" + assignTime + ", agreements="
				+ agreements + ", risk_revelation=" + riskRevelation + ", match_instruction=" + matchInstruction
				+ "]";
	}
	
	
}
