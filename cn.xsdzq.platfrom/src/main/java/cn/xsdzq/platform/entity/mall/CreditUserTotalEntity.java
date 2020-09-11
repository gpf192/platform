package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 用户总积分， 单独建表，如果动态计算，后期导入记录过多，导致计算压力过大
 * 与mall_user 表分开
 * @author ThinkPad-T430
 *注：增加定时任务，跑批处理过期分数
 */
@Entity
@Table(name = "mall_user_total_credit")
@EntityListeners(AuditingEntityListener.class)
public class CreditUserTotalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_credit_sequence")
	@SequenceGenerator(name = "user_credit_sequence", sequenceName = "sequence_user_credit", allocationSize = 1)
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
	
	@Column(name = "total")
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

	public String getMobile() {
		return mobile;
	}

	public void setMobil(String mobile) {
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "UserTotalCreditEntity [id=" + id + ", clientName=" + clientName + ", clientId=" + clientId + ", mobile="
				+ mobile + ", departmentDesc=" + departmentDesc + ", departmentCode=" + departmentCode + ", total="
				+ total + "]";
	}
	

	
}
