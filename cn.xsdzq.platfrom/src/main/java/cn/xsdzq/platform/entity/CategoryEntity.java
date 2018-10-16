package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@Lob
	@Column(name = "image", length = 2000, nullable = true)
	private String image;

	@Column(name = "categorysort", columnDefinition = "int DEFAULT 0")
	private int categorysort;
	
	@Column(name = "display_flag")
	private boolean displayFlag ;//前台显示标识
	
	@Column(name = "exp")
	private String exp;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE,
			CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "categoryEntity")

	private Set<InfoEntity> infos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategorysort() {
		return categorysort;
	}

	public void setCategorysort(int categorysort) {
		this.categorysort = categorysort;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public Set<InfoEntity> getInfos() {
		return infos;
	}

	public void setInfos(Set<InfoEntity> infos) {
		this.infos = infos;
	}

	public boolean getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(boolean displayFlag) {
		this.displayFlag = displayFlag;
	}

}
