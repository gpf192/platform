package cn.xsdzq.platform.model.lcj;

import java.util.Date;

import javax.persistence.Column;

public class ProductSellDTO {
	private String clientId;//客户号
	
	private String clientName;//客户姓名
	
	private String productCode;//产品编号
	
	private String productName;//产品名称
	
	private String financeAccount;//金融账号
	
	private int dealTime;//成交日期
	
	private String dealAmount;//成交金额
	
	private String empName;//对应服务员工
		
	private int flag;//0-场内  1-场外

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFinanceAccount() {
		return financeAccount;
	}

	public void setFinanceAccount(String financeAccount) {
		this.financeAccount = financeAccount;
	}

	public int getDealTime() {
		return dealTime;
	}

	public void setDealTime(int dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ProductSellDTO [clientId=" + clientId + ", clientName=" + clientName + ", productCode=" + productCode
				+ ", productName=" + productName + ", financeAccount=" + financeAccount + ", dealTime=" + dealTime
				+ ", dealAmount=" + dealAmount + ", empName=" + empName + ", flag=" + flag + "]";
	}

	


}
