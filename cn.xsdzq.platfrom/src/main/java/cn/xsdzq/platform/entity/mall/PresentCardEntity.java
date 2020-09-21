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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 * 实物卡
 * @author Administrator
 *
 */
@Entity
@Table(name = "mall_present_card")
@EntityListeners(AuditingEntityListener.class)
public class PresentCardEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_present_card")
	@SequenceGenerator(name = "sequence_present_card", sequenceName = "sequence_present_card", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "card_id", unique = true)
	private String cardId;

	@Column(name = "password")
	private String password;

	@Column(name = "card_status")
	private int cardStatus;//卡券状态， 上架1/下架0

	@Column(name = "convert_status")
	private int convertStatus;//兑换状态，已兑换1/未兑换0
	
	@Column(name = "presentId", insertable = false, updatable = false)
	private long presentId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "presentId", referencedColumnName = "id")
	private PresentEntity present;//二级目录

	@Column(name = "create_date")
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "convert_date")
	private Date convertDate;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "modifyBy")
	private String modifyBy;

	// 修改时间
	@Column(name = "modifytime")
	private Date modifytime;
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}


		
	public Date getModifytime() {
			return modifytime;
		}

		public void setModifytime(Date modifytime) {
			this.modifytime = modifytime;
		}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public int getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}

	public int getConvertStatus() {
		return convertStatus;
	}

	public void setConvertStatus(int convertStatus) {
		this.convertStatus = convertStatus;
	}

	public PresentEntity getPresent() {
		return present;
	}

	public void setPresent(PresentEntity present) {
		this.present = present;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getConvertDate() {
		return convertDate;
	}

	public void setConvertDate(Date convertDate) {
		this.convertDate = convertDate;
	}

	public long getPresentId() {
		return presentId;
	}

	public void setPresentId(long presentId) {
		this.presentId = presentId;
	}

	@Override
	public String toString() {
		return "PresentCardEntity [id=" + id + ", cardId=" + cardId + ", password=" + password + ", cardStatus="
				+ cardStatus + ", convertStatus=" + convertStatus + ", presentId=" + presentId + ", present=" + present
				+ ", createDate=" + createDate + ", convertDate=" + convertDate + ", createdBy=" + createdBy
				+ ", modifyBy=" + modifyBy + ", modifytime=" + modifytime + "]";
	}


}
