package cn.xsdzq.platform.model.mall;

import java.io.Serializable;

public class UserBlacklistDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String clientId;
	
	private String clientName;
	
	private String departmentDesc;
	
	private String createdBy;
	
	private String createDate;
	private int isNew;

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

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

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreateDate() {
		return createDate;
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

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserBlackllistDTO [id=" + id + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", departmentDesc=" + departmentDesc + ", createdBy=" + createdBy + ", createDate=" + createDate
				+ "]";
	}
	

}
