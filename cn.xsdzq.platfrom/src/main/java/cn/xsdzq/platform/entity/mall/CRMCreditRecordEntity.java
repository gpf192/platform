package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_crm_credit_record")
@EntityListeners(AuditingEntityListener.class)
public class CRMCreditRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "serialNum")
	private String serialNum;
	
	@Column(name = "clientId")
	private String clientId;
	
	@Column(name = "clientName")
	private String clientName;
	// 项目名称，
	@Column(name = "mobile")
	private String mobile;	//导入时的手机号
		
	// 项目名称，
	@Column(name = "itemName")
	private String itemName;//前端展示

		// 项目代码，此处不与项目类关联， 保持导入记录明细，避免项目类被删除后，找不到记录
	@Column(name = "itemCode")
	private String itemCode;
	
	@Column(name = "departmentDesc")
	private String departmentDesc;
	
	@Column(name = "departmentCode")
	private String departmentCode;
	// 积分数
	@Column(name = "num")
	private int num;

	
	@Column(name = "beginDate")
	private int beginDate;//生效日期
	
	@Column(name = "endDate")
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
		return "CRMCreditRecordEntity [serialNum=" + serialNum + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", mobile=" + mobile + ", itemName=" + itemName + ", itemCode=" + itemCode + ", departmentDesc="
				+ departmentDesc + ", departmentCode=" + departmentCode + ", num=" + num + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}


	
}
