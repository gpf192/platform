package cn.xsdzq.platform.model.lcj;

import java.util.Date;

import javax.persistence.Column;

public class ProductSellDTO {
	private long id;
	
	private String username;
	
	private String account;//资金账号
	
	private String product_code;//产品代码
	
	private String product_name;
	
	private String deal_share;//成交份额
	
	private String deal_amount;//成交金额
	
	private String votes;//获得票数
	
	private String emp_name;//对应服务员工
	
	private Date order_time;//委托时间
	
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

	public ProductSellDTO(long id, String username, String account, String product_code, String product_name,
			String deal_share, String deal_amount, String votes, String emp_name, Date order_time, Date deal_time) {
		super();
		this.id = id;
		this.username = username;
		this.account = account;
		this.product_code = product_code;
		this.product_name = product_name;
		this.deal_share = deal_share;
		this.deal_amount = deal_amount;
		this.votes = votes;
		this.emp_name = emp_name;
		this.order_time = order_time;
		this.deal_time = deal_time;
	}

	public ProductSellDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ProductSellDTO [id=" + id + ", username=" + username + ", account=" + account + ", product_code="
				+ product_code + ", product_name=" + product_name + ", deal_share=" + deal_share + ", deal_amount="
				+ deal_amount + ", votes=" + votes + ", emp_name=" + emp_name + ", order_time=" + order_time
				+ ", deal_time=" + deal_time + "]";
	}
	
	
}
