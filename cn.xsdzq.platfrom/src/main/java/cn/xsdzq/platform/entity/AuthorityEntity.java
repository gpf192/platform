package cn.xsdzq.platform.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements GrantedAuthority, Comparable<AuthorityEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, length = 20)
	private Long id;

	@Column(name = "menu_name", unique = true, nullable = false, length = 100)
	private String menu_name;

	@Column(name = "authority", unique = true, nullable = false, length = 50)
	private String authority;

	@Column(name = "url")
	private String url;

	@Column(name = "forward")
	private String forward;

	@Column(name = "parent_id", nullable = false)
	private long parent_id;

	@Column(name = "resource_path", length = 100)
	private String resource_path;

	@Column(name = "level")
	private int level;

	@Column(name = "sort")
	private int sort;

	@Transient
	private boolean check;

	// 创建时间
	@Column(name = "createtime", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true, insertable = false, updatable = false, columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
	private Date modifytime;

	@ManyToMany(mappedBy = "authorityEntities", fetch = FetchType.EAGER)
	private Set<RoleEntity> roleEntities;

	public AuthorityEntity() {
		super();
	}

	public AuthorityEntity(Long id, String menu_name, String authority, String url, String forward, long parent_id,
			String resource_path, int level, int sort, boolean check, Date createtime, Date modifytime,
			Set<RoleEntity> roleEntities) {
		super();
		this.id = id;
		this.menu_name = menu_name;
		this.authority = authority;
		this.url = url;
		this.forward = forward;
		this.parent_id = parent_id;
		this.resource_path = resource_path;
		this.level = level;
		this.sort = sort;
		this.check = check;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.roleEntities = roleEntities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getParent_id() {
		return parent_id;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public String getResource_path() {
		return resource_path;
	}

	public void setResource_path(String resource_path) {
		this.resource_path = resource_path;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Set<RoleEntity> getRoleEntities() {
		return roleEntities;
	}

	@JsonBackReference
	public void setRoleEntities(Set<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

	@Override
	public int compareTo(AuthorityEntity authority) {
		// TODO Auto-generated method stub
		if (this.sort > authority.getSort()) {
			return 1;
		}
		return -1;
	}

}
