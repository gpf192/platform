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
@Table(name = "authorities")
public class AuthoritiesEntity implements Serializable {

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

	@Column(name = "authority", unique = true, nullable = false, length = 50)
	private String authority;

	@ManyToMany
	@JoinTable(name = "users_authorities", joinColumns = { @JoinColumn(name = "authority_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })

	private Set<UserEntity> userEntitySet;

	// 创建时间
	@Column(name = "createtime", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createtime;

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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<UserEntity> getUserEntitySet() {
		return userEntitySet;
	}

	@JsonBackReference
	public void setUserEntitySet(Set<UserEntity> userEntitySet) {
		this.userEntitySet = userEntitySet;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
