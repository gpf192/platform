package cn.xsdzq.platform.model.lcj;

import java.util.Date;

import javax.persistence.Column;

public class ProductDTO {
	private long id;
	private String name;
	private String code;
	private String type;
	private String coefficient;
	
	private String beginDate;
	
	private String endDate;	
	private String initialAmount;//起始金额
	
	private int flag;//0-场内  1-场外
	private String riskLevel; //分险等级
	
	private String preferentialInfo;
	private int scanFlag ;

	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getPreferentialInfo() {
		return preferentialInfo;
	}
	public void setPreferentialInfo(String preferentialInfo) {
		this.preferentialInfo = preferentialInfo;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(String initialAmount) {
		this.initialAmount = initialAmount;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public ProductDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}
	
	
	public int getScanFlag() {
		return scanFlag;
	}
	public void setScanFlag(int scanFlag) {
		this.scanFlag = scanFlag;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", code=" + code + ", type=" + type + ", coefficient="
				+ coefficient + ", beginDate=" + beginDate + ", endDate=" + endDate + ", initialAmount=" + initialAmount
				+ ", flag=" + flag + "]";
	}
	
}
