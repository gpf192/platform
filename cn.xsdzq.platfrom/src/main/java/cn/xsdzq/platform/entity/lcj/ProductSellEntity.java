package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj_product_sell_view")
@EntityListeners(AuditingEntityListener.class)
public class ProductSellEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "lsh")
	private String lsh;
	
	@Column(name = "client_id")
	private String clientId;//客户号
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "finance_account")
	private String financeAccount;//金融账号
	
	@Column(name = "deal_time")
	private int dealTime;//成交日期
	
	@Column(name = "deal_amount")
	private String dealAmount;//成交金额
	
	@Column(name = "emp_name")
	private String empName;//对应服务员工
		
	@Column(name = "flag")
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

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Override
	public String toString() {
		return "ProductSellEntity [lsh=" + lsh + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", productCode=" + productCode + ", productName=" + productName + ", financeAccount=" + financeAccount
				+ ", dealTime=" + dealTime + ", dealAmount=" + dealAmount + ", empName=" + empName + ", flag=" + flag
				+ "]";
	}
		
	
}
