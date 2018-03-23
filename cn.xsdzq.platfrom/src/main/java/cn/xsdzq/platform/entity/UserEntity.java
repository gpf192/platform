package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, length = 20)
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "password", nullable = false, length = 20)
	private String password;

	@Column(name = "phone", nullable = true, length = 12)
	private String phone;

	@Column(name = "department", nullable = true, length = 50)
	private String department;

	@Column(name = "address", nullable = true, length = 100)
	private String address;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;

	@ManyToMany
	@JoinTable(name = "users_authorities", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_id", referencedColumnName = "id") })
	private Set<AuthoritiesEntity> authoritiesEntitySet;

	// 创建时间
	@Column(name = "createtime", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true, insertable = false, updatable = false, columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
	private Date modifytime;

	@Column(name = "lastmodifytime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastmodifytime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<AuthoritiesEntity> getAuthoritiesEntitySet() {
		return authoritiesEntitySet;
	}

	@JsonBackReference
	public void setAuthoritiesEntitySet(Set<AuthoritiesEntity> authoritiesEntitySet) {
		this.authoritiesEntitySet = authoritiesEntitySet;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Date getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

}
