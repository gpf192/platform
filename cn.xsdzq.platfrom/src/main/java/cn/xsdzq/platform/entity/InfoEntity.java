package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "information", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
@EntityListeners(AuditingEntityListener.class)
public class InfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_sequence")
	@SequenceGenerator(name = "info_sequence", sequenceName = "sequence_info", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "title")

	private String title;
	// add by fjx begin
	@Column(name = "checked")
	private String checked = "N";// 默认未审核

	@Column(name = "checked_result")
	private String checkedResult;

	@Column(name = "created_by")
	private String created_by;
	// add by fjx end

	@Column(name = "categoryId", insertable = false, updatable = false)
	private long categoryId;

	@Column(name = "label")
	private String label;

	@Lob
	@Column(name = "content", length = 3000)
	private String content;

	@Column(name = "exp")
	private String exp;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")

	private CategoryEntity categoryEntity;

	// 创建时间

	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime")
	@LastModifiedDate
	private Date modifytime;

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

	public String getCheckedResult() {
		return checkedResult;
	}

	public void setCheckedResult(String checkedResult) {
		this.checkedResult = checkedResult;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	@JsonBackReference
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

}
