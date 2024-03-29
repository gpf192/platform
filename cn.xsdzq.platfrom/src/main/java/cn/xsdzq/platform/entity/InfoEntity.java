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
	
	@Column(name = "common_flag")
	private String commonFlag = "N";//前台常见问题标识

	@Column(name = "checked_result")
	private String checkedResult;//generate-暂存等待提交，submit-提交未审核，approve-审核通过，reject-审核拒绝；

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "approved_by")
	private String approvedBy = "";
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

	@Column(name = "weight")
	private int weight = 0;// 设置信息权重，初始为0

	@Column(name = "page_view")
	private int pageView = 0;

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
	// 审批时间
	@Column(name = "approve_time")
	private Date approveTime;
	
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getPageView() {
		return pageView;
	}

	public void setPageView(int pageView) {
		this.pageView = pageView;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCommonFlag() {
		return commonFlag;
	}

	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@JsonBackReference
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

}
