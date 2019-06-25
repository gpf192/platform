package cn.xsdzq.platform.model.lcj;

public class PrizeDTO {
	private long id;
	private String name;
	private String pice;
	private String rate;
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
	@Override
	public String toString() {
		return "PrizeDTO [id=" + id + ", name=" + name + ", pice=" + pice + ", rate=" + rate + "]";
	}
	public PrizeDTO(long id, String name, String pice, String rate) {
		super();
		this.id = id;
		this.name = name;
		this.pice = pice;
		this.rate = rate;
	}
	
}
