package cn.xsdzq.platform.model.mall;

import java.util.Date;

public class CreditImportRecordDTO {
	private long id;
	private String clientName;//客户姓名
	private String clientId;//客户id
	private String mobile;//手机号，每次导入即更新
	private String departmentDesc;//营业部名称
	private String departmentCode;//营业部编码, 每次导入即更新
	private String categoryName;//项目名称
	private String categoryCode;//项目编码，唯一
	private String num;//导入分数
	private String beginDate;//生效日期
	private String endDate;//失效日期	
	private String recordTime;
	private String importItem;	//导入时关联的项目名称
	private String importMobile;//导入时的手机号

	public String getImportMobile() {
		return importMobile;
	}

	public void setImportMobile(String importMobile) {
		this.importMobile = importMobile;
	}

	public String getImportItem() {
		return importItem;
	}

	public void setImportItem(String importItem) {
		this.importItem = importItem;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}



	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "CreditImportRecordDTO [id=" + id + ", clientName=" + clientName + ", clientId=" + clientId + ", mobile="
				+ mobile + ", departmentDesc=" + departmentDesc + ", departmentCode=" + departmentCode
				+ ", categoryName=" + categoryName + ", categoryCode=" + categoryCode + ", num=" + num + ", beginDate="
				+ beginDate + ", endDate=" + endDate + ", recordTime=" + recordTime + ", importItem=" + importItem
				+ ", importMobile=" + importMobile + "]";
	}

	
}
