package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_credit_import_Temp")
@EntityListeners(AuditingEntityListener.class)
public class CreditImportTempEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_temp_sequence")
	@SequenceGenerator(name = "credit_temp_sequence", sequenceName = "sequence_credit_temp", allocationSize = 1)
	@Column(name = "id")
	private long id;
	

	@Column(name = "client_name")
	private String clientName;//客户姓名
	
	@Column(name = "client_id")
	private String clientId;//客户id
	
	@Column(name = "mobile")
	private String mobile;//手机号，每次导入即更新
	
	@Column(name = "department_desc")
	private String departmentDesc;//营业部名称
	
	@Column(name = "department_code")
	private String departmentCode;//营业部编码, 每次导入即更新
	
	@Column(name = "category_name")
	private String categoryName;//项目名称
	
	@Column(name = "category_code")
	private String categoryCode;//项目编码
	
	@Column(name = "num")
	private int num;//导入分数
	
	@Column(name = "begin_date")
	private String beginDate;//生效日期
	
	@Column(name = "end_date")
	private String endDate;//失效日期

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


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
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
		return "CreditImportRecordEntity [id=" + id + ", clientName=" + clientName + ", clientId=" + clientId
				+ ", mobile=" + mobile + ", departmentDesc=" + departmentDesc + ", departmentCode=" + departmentCode
				+ ", categoryName=" + categoryName + ", categoryCode=" + categoryCode + ", num=" + num + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}
}
