package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj_product_sell_record")
@EntityListeners(AuditingEntityListener.class)
public class ProductSellEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sell_sequence")
	@SequenceGenerator(name = "product_sell_sequence", sequenceName = "sequence_product_sell", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "clientId")
	private String clientId;//客户号
	
	@Column(name = "username")
	private String username;//客户姓名
	
	@Column(name = "account")
	private String zj;//资金账号
	
	@Column(name = "finaccount")
	private String lc;//理财账号
	
	@Column(name = "code")
	private String code;//产品代码
	
	@Column(name = "product_name")
	private String productName; //产品名称
	
	@Column(name = "product_type")
	private String productType; //产品类型
	
	@Column(name = "found_type")
	private String foundType; //基金类型。0-场内，1-场外
	
	@Column(name = "deal_share")
	private String dealShare;//成交份额
	
	@Column(name = "deal_amount")
	private String dealAmount;//成交金额
	
	@Column(name = "votes")
	private String votes;//获得票数
	
	@Column(name = "emp_name")
	private String empName;//对应服务员工
	
	@Column(name = "order_time")
	private Date orderTime;//委托时间
	
	@Column(name = "deal_time")
	private Date dealTime;//成交时间
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	


	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getFoundType() {
		return foundType;
	}
	public void setFoundType(String foundType) {
		this.foundType = foundType;
	}
	public String getDealShare() {
		return dealShare;
	}
	public void setDealShare(String dealShare) {
		this.dealShare = dealShare;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	@Override
	public String toString() {
		return "ProductSellEntity [id=" + id + ", clientId=" + clientId + ", username=" + username + ", zj=" + zj
				+ ", lc=" + lc + ", productCode=" + code + ", productName=" + productName + ", productType="
				+ productType + ", foundType=" + foundType + ", dealShare=" + dealShare + ", dealAmount=" + dealAmount
				+ ", votes=" + votes + ", empName=" + empName + ", orderTime=" + orderTime + ", dealTime=" + dealTime
				+ "]";
	}


	
}
