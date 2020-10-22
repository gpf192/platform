package cn.xsdzq.platform.model.mall;


public class CRMCreditRecordDTO {
	private String serialNum;	
	private String clientId;
	private String clientName;
	private String mobile;		
	private String itemName;
	private String itemCode;
	private String departmentDesc;
	
	private String departmentCode;
	private int num;	
	private int beginDate;//生效日期	
	private int endDate;//失效日期
	
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(int beginDate) {
		this.beginDate = beginDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "CRMCreditRecordDTO [serialNum=" + serialNum + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", mobile=" + mobile + ", itemName=" + itemName + ", itemCode=" + itemCode + ", departmentDesc="
				+ departmentDesc + ", departmentCode=" + departmentCode + ", num=" + num + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}
	
	
}
