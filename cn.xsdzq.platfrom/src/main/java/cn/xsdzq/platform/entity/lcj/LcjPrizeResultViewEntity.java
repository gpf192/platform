package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj818_prize_result_view")
@EntityListeners(AuditingEntityListener.class)
public class LcjPrizeResultViewEntity implements Serializable {
	@Id
	private long id;

	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "prize_name")
	private String prizeName;
	
	@Column(name = "prize_code")
	private String prizeCode;

	
	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	@Column(name = "depart_name")
	private String departName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeCode() {
		return prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}
}
