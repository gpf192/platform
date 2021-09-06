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
@Table(name = "mall_user_blacklist_temp")
@EntityListeners(AuditingEntityListener.class)
public class UserBlackListImportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user_blacklist")
	@SequenceGenerator(name = "sequence_user_blacklist", sequenceName = "sequence_user_blacklist", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "client_id", unique = true)
	private String clientId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "department_desc")
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
		return "UserBlackListImportEntity [id=" + id + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", departmentDesc=" + departmentDesc + "]";
	}
	
}
