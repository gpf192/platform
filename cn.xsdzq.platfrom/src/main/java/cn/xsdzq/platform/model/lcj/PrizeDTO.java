package cn.xsdzq.platform.model.lcj;

import java.io.Serializable;

public class PrizeDTO implements Serializable{
	private long id;
	private String name;
	private String pice;
	private String rate;
	private String amount;
	private Integer winning_number;
	
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
	public String getPice() {
		return pice;
	}
	public void setPice(String pice) {
		this.pice = pice;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
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
		return "PrizeDTO [id=" + id + ", name=" + name + ", pice=" + pice + ", rate=" + rate + "]";
	}
	public PrizeDTO(long id, String name, String pice, String rate ,String amount) {
		super();
		this.id = id;
		this.name = name;
		this.pice = pice;
		this.rate = rate;
		this.amount = amount;
	}
	
}
