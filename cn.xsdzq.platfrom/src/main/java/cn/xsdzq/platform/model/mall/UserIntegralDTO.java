package cn.xsdzq.platform.model.mall;

public class UserIntegralDTO {
	private long id;//用户总积分表主键
	private String flag;//1-加  ，0-减
	private String clientId;
	private int changeNum;
	private String categoryCode;//变更理由
	private String categoryName;//变更理由
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getId() {
		return id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getFlag() {
		return flag;
	}
	public int getChangeNum() {
		return changeNum;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setChangeNum(int changeNum) {
		this.changeNum = changeNum;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	@Override
	public String toString() {
		return "UserIntegralDTO [id=" + id + ", flag=" + flag + ", clientId=" + clientId + ", changeNum=" + changeNum
				+ ", categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
	
	
}
