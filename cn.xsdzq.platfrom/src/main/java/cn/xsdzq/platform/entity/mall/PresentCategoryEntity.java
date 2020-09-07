package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.xsdzq.platform.entity.InfoEntity;
/**
 * 商品分类，一级目录
 * @author Administrator
 *
 */
@Entity
@Table(name = "mall_present_category")
@EntityListeners(AuditingEntityListener.class)
public class PresentCategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_Category_sequence")
	@SequenceGenerator(name = "present_Category_sequence", sequenceName = "present_Category_sequence", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "flag")
	private String flag;//是否启用

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifytime;
	
	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE,
			CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "presentCategoryEntity")
	private Set<PresentEntity> presentEntitys;*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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



	/*public Set<PresentEntity> getPresentEntitys() {
		return presentEntitys;
	}

	public void setPresentEntitys(Set<PresentEntity> presentEntitys) {
		this.presentEntitys = presentEntitys;
	}*/

	@Override
	public String toString() {
		return "PresentCategory [id=" + id + ", name=" + name + ", flag=" + flag + ", createtime=" + createtime
				+ ", modifytime=" + modifytime + "]";
	}

}
