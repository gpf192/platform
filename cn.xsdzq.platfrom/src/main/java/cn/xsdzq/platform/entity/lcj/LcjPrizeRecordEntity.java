package cn.xsdzq.platform.entity.lcj;

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
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name = "lcj818_prize_record")
@EntityListeners(AuditingEntityListener.class)
public class LcjPrizeRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, length = 20)
	private Long id;

	@Column(name = "type", nullable = false)
	private boolean type; // type 为0 表示减少 reason 以0开头 0X， 为1表示增加 reason 以1开头 1X

	@Column(name = "reason", nullable = false)
	private String reason;

	@Column(name = "num", nullable = false)
	private Integer number = 0; // 增加或者减少的数量,默认为0
	
	@Column(name = "data_flag", nullable = false)
	private String dateFlag; // 每日的判断标准

	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private LcjUserEntity userEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public LcjUserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(LcjUserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
