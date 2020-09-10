package cn.xsdzq.platform.model.mall;

public class CreditUserTotalDTO {
	private long id;	
	private String clientName;//客户姓名
	private String clientId;//客户id
	private String mobil;//手机号，每次导入即更新
	private String departmentDesc;//营业部名称
	private String departmentCode;//营业部编码, 每次导入即更新
	private long total;//总分数
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
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
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "CreditUserTotalDTO [id=" + id + ", clientName=" + clientName + ", clientId=" + clientId + ", mobil="
				+ mobil + ", departmentDesc=" + departmentDesc + ", departmentCode=" + departmentCode + ", total="
				+ total + "]";
	}
	
	
}
