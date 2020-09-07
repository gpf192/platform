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
/**
 * 积分明细导入记录
 * @author ThinkPad-T430
 *注：
 */
@Entity
@Table(name = "mall_credit_import_record")
@EntityListeners(AuditingEntityListener.class)
public class CreditImportRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_import_sequence")
	@SequenceGenerator(name = "credit_import_sequence", sequenceName = "sequence_credit_import", allocationSize = 1)
	@Column(name = "id")
	private long id;
	

	@Column(name = "client_name")
	private String clientName;//客户姓名
	
	@Column(name = "client_id")
	private String clientId;//客户id
	
	@Column(name = "mobil")
	private String mobil;//手机号，每次导入即更新
	
	@Column(name = "department_desc")
	private String departmentDesc;//营业部名称
	
	@Column(name = "department_code")
	private String departmentCode;//营业部编码, 每次导入即更新
	
	@Column(name = "category_name")
	private String categoryName;//项目名称
	
	@Column(name = "category_code", unique = true)
	private String categoryCode;//项目编码，唯一
	
	@Column(name = "num")
	private long num;//导入分数
	
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

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
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
				+ ", mobil=" + mobil + ", departmentDesc=" + departmentDesc + ", departmentCode=" + departmentCode
				+ ", categoryName=" + categoryName + ", categoryCode=" + categoryCode + ", num=" + num + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}

	
}
