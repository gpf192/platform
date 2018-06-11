package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, length = 20)
	private Long id;

	@Column(name = "name", unique = true, length = 50)
	private String name;

	@Column(name = "parent_id", length = 50)
	private Long parent_id;// 父级角色ID

	@Column(name = "role_describe", length = 100)
	private String describe;// 描述

	@Column(name = "sort")
	private int sort;// 排序序号

	// 创建时间
	@CreatedDate
	@Column(name = "createtime")
	private Date createtime;

	// 修改时间
	@LastModifiedDate
	@Column(name = "modifytime", nullable = true)
	private Date modifytime;

	@ManyToMany(targetEntity = AuthorityEntity.class, fetch = FetchType.EAGER)
	@JoinTable(name = "roles_authorities", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "authority_id") })
	private Set<AuthorityEntity> authorityEntities;

	@ManyToMany(mappedBy = "roleEntities", fetch = FetchType.EAGER)
	private Set<UserEntity> userEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public Set<UserEntity> getUserEntities() {
		return userEntities;
	}

	@JsonBackReference
	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	public Set<AuthorityEntity> getAuthorityEntities() {
		return authorityEntities;
	}

	@JsonBackReference
	public void setAuthorityEntities(Set<AuthorityEntity> authorityEntities) {
		this.authorityEntities = authorityEntities;
	}

}
