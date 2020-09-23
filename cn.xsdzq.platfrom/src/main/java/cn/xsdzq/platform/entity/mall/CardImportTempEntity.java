package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 批量导入临时类
 * @author Administrator
 *
 */
@Entity
@Table(name = "mall_present_card_import_temp")
@EntityListeners(AuditingEntityListener.class)
public class CardImportTempEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_card_import_temp")
	@SequenceGenerator(name = "sequence_card_import_temp", sequenceName = "sequence_card_import_temp", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "card_id")
	private String cardId;

	@Column(name = "password")
	private String password;

	@Column(name = "card_status")
	private int cardStatus = 1 ;//卡券状态， 上架1/下架0

	@Column(name = "convert_status")
	private int convertStatus = 0;//兑换状态，已兑换1/未兑换0
	
	@Column(name = "presentCode")
	private String presentCode;//关联父项

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

	public String getPresentCode() {
		return presentCode;
	}

	public void setPresentCode(String presentCode) {
		this.presentCode = presentCode;
	}

	@Override
	public String toString() {
		return "CardImportTempEntity [id=" + id + ", cardId=" + cardId + ", password=" + password + ", cardStatus="
				+ cardStatus + ", convertStatus=" + convertStatus + ", presentCode=" + presentCode + "]";
	}
	
	
}
