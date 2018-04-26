package cn.xsdzq.platform.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "information", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class InfoEntity implements Serializable {

	/**
	 * 
	 */
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
	//add by fjx begin
	@Column(name = "checked")
	private String checked = "N";
	@Column(name = "checked_result")
	private String checked_result = "false";
	//add by fjx end

	@Column(name = "categoryId", insertable = false, updatable = false)
	private long categoryId;

	@Column(name = "label")
	private String label;

	@Column(name = "content")
	private String content;

	@Column(name = "exp")
	private String exp;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")

	private CategoryEntity categoryEntity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getChecked_result() {
		return checked_result;
	}

	public void setChecked_result(String checked_result) {
		this.checked_result = checked_result;
	}

	@JsonBackReference
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

}
