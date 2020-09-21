package cn.xsdzq.platform.model.mall;

public class PresentResultDTO {
	private String clientName;
	private String clientId;
	private String departmentName;
	private String mobile;
	private String presentName;
	private String cardId;
	private String password;
	private float price;
	private int integralNum;
	private String recordTime;
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPresentName() {
		return presentName;
	}
	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getIntegralNum() {
		return integralNum;
	}
	public void setIntegralNum(int integralNum) {
		this.integralNum = integralNum;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	@Override
	public String toString() {
		return "PresentResultDTO [clientName=" + clientName + ", clientId=" + clientId + ", departmentName="
				+ departmentName + ", mobile=" + mobile + ", presentName=" + presentName + ", cardId=" + cardId
				+ ", password=" + password + ", price=" + price + ", integralNum=" + integralNum + ", recordTime="
				+ recordTime + "]";
	}
	
}
