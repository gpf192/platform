package cn.xsdzq.platform.model.lcj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;

public class PrizeDTO implements Serializable{
	private long id;

	private String name;

	private String price;

	private String rate; // 动态计算

	private String image;

	private Integer amount;

	private boolean type;

	private boolean isShow = false;

	private int winningNumber = 0;


	private String createtime;
	

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
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
	public int getWinningNumber() {
		return winningNumber;
	}
	public void setWinningNumber(int winningNumber) {
		this.winningNumber = winningNumber;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public PrizeDTO() {
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
	
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	

	
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PrizeDTO [id=" + id + ", name=" + name + ", price=" + price + ", rate=" + rate + ", image=" + image
				+ ", amount=" + amount + ", type=" + type + ", isShow=" + isShow + ", winningNumber=" + winningNumber
				+ ", createtime=" + createtime + "]";
	}
	
	
	
}
