package cn.xsdzq.platform.entity.lcj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj_product_sell_record")
@EntityListeners(AuditingEntityListener.class)
public class ProductSellEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sell_sequence")
	@SequenceGenerator(name = "product_sell_sequence", sequenceName = "sequence_product_sell", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "account")
	private String account;//资金账号
	
	@Column(name = "product_code")
	private String product_code;//产品代码
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "deal_share")
	private String deal_share;//成交份额
	
	@Column(name = "deal_amount")
	private String deal_amount;//成交金额
	
	@Column(name = "votes")
	private String votes;//获得票数
	
	@Column(name = "emp_name")
	private String emp_name;//对应服务员工
	
	@Column(name = "order_time")
	private Date order_time;//委托时间
	
	@Column(name = "deal_time")
	private Date deal_time;//成交时间
	
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDeal_share() {
		return deal_share;
	}
	public void setDeal_share(String deal_share) {
		this.deal_share = deal_share;
	}
	public String getDeal_amount() {
		return deal_amount;
	}
	public void setDeal_amount(String deal_amount) {
		this.deal_amount = deal_amount;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getDeal_time() {
		return deal_time;
	}
	public void setDeal_time(Date deal_time) {
		this.deal_time = deal_time;
	}
	@Override
	public String toString() {
		return "ProductSellEntity [id=" + id + ", username=" + username + ", account=" + account + ", product_code="
				+ product_code + ", product_name=" + product_name + ", deal_share=" + deal_share + ", deal_amount="
				+ deal_amount + ", votes=" + votes + ", emp_name=" + emp_name + ", order_time=" + order_time
				+ ", deal_time=" + deal_time + "]";
	}
	
	
	
}
