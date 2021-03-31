package cn.xsdzq.platform.model.mall;

import java.io.Serializable;

public class UserBlacklistImortDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String clientId;
	
	private String clientName;
	
	private String departmentDesc;

	public long getId() {
		return id;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	@Override
	public String toString() {
		return "UserBlacklistImortDTO [id=" + id + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", departmentDesc=" + departmentDesc + "]";
	}
	
}
