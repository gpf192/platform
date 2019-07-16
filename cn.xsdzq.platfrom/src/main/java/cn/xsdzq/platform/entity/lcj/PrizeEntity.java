package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj_prize_info")
@EntityListeners(AuditingEntityListener.class)
public class PrizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prize_sequence")
	@SequenceGenerator(name = "prize_sequence", sequenceName = "sequence_prize", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "rate")
	private String rate;
	
	@Column(name = "amount")//奖品总量
	private String amount;
	
	// 创建时间

	@Column(name = "createtime")
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime")
	private Date modifytime;
	
	@Column(name = "winning_number",columnDefinition="int default 0")//中奖人数
	private int winning_number = 0;
	
	@Column(name = "type") // 奖品类型
	private boolean type;
	
	@Column(name = "isShow") // 默认不显示
	private boolean isShow = false;
	
	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	public Integer getWinning_number() {
		return winning_number;
	}

	public void setWinning_number(Integer winning_number) {
		this.winning_number = winning_number;
	}

	@Override
	public String toString() {
		return "PrizeEntity [id=" + id + ", name=" + name + ", price=" + price + ", rate=" + rate + ", amount=" + amount
				+ ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}
	
	
}
