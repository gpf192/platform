package cn.xsdzq.platform.entity.mall;

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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * 商品管理，二级目录
 * @author Administrator
 *
 */
@Entity
@Table(name = "mall_present")
@EntityListeners(AuditingEntityListener.class)
public class PresentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_sequence")
	@SequenceGenerator(name = "present_sequence", sequenceName = "present_sequence", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "name", unique = true)
	private String name;
	
	@Lob
	@Column(name = "image", nullable = true)
	private String image;
	
	@Column(name = "face_value", precision = 2)
	private float faceValue;//面值

	@Column(name = "value", precision = 2)
	private float value;//实际价格

	@Column(name = "description")
	private String description;

	@Column(name = "store_number")
	private int storeNumber;//总库存

	@Column(name = "convert_number")
	private int convertNumber;//已兑换

	@Column(name = "store_unused")
	private int storeUnused;//剩余库存

	@Column(name = "status")
	private String status;//状态，上上架/下架
	
	@Column(name = "categoryId", insertable = false, updatable = false)
	private long categoryId;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private PresentCategoryEntity presentCategory;//商品分类

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;
	 
	 @Column(name = "big_image")
	 private String bigImage;
	 
	 @Column(name = "is_hot")
	 private boolean isHot;
	 
	 @Column(name = "tip")
	 private String tip;
	 
	 
	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

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

	public float getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(float faceValue) {
		this.faceValue = faceValue;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}

	public int getConvertNumber() {
		return convertNumber;
	}

	public void setConvertNumber(int convertNumber) {
		this.convertNumber = convertNumber;
	}

	public int getStoreUnused() {
		return storeUnused;
	}

	public void setStoreUnused(int storeUnused) {
		this.storeUnused = storeUnused;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PresentCategoryEntity getPresentCategory() {
		return presentCategory;
	}

	@JsonBackReference
	public void setPresentCategory(PresentCategoryEntity presentCategory) {
		this.presentCategory = presentCategory;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "PresentEntity [id=" + id + ", name=" + name + ", image=" + image + ", faceValue=" + faceValue
				+ ", value=" + value + ", description=" + description + ", storeNumber=" + storeNumber
				+ ", convertNumber=" + convertNumber + ", storeUnused=" + storeUnused + ", status=" + status
				+ ", categoryId=" + categoryId + ", presentCategory=" + presentCategory + ", createtime=" + createtime
				+ ", modifytime=" + modifytime + ", bigImage=" + bigImage + ", isHot=" + isHot + ", tip=" + tip + "]";
	}

	


}
