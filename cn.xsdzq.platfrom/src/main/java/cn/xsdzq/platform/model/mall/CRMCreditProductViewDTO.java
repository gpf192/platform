package cn.xsdzq.platform.model.mall;

public class CRMCreditProductViewDTO {
	private String productCode;//产品代码
	
	private String productName;//产品代码
	
	private String fundType;//基金类型
	
	private String riskType;//风险类型
	
	private String productType;//产品类型
	
	private String purchaseRate;//申购费率

	public String getProductCode() {
		return productCode;
	}

	public String getProductName() {
		return productName;
	}

	public String getFundType() {
		return fundType;
	}

	public String getRiskType() {
		return riskType;
	}

	public String getProductType() {
		return productType;
	}

	public String getPurchaseRate() {
		return purchaseRate;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setPurchaseRate(String purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	@Override
	public String toString() {
		return "CRMCreditProductViewDTO [productCode=" + productCode + ", productName=" + productName + ", fundType="
				+ fundType + ", riskType=" + riskType + ", productType=" + productType + ", purchaseRate="
				+ purchaseRate + "]";
	}
}
