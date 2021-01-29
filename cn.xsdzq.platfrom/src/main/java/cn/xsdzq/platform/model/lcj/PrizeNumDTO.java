package cn.xsdzq.platform.model.lcj;

public class PrizeNumDTO {
	private Long id;
	private Integer number ;
	private String clientName;
	private String clientId;
	private String phone;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getNumber() {
		return number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "PrizeNumDTO [id=" + id + ", number=" + number + ", clientName=" + clientName + ", clientId=" + clientId
				+ ", phone=" + phone + "]";
	}
	
	
}
