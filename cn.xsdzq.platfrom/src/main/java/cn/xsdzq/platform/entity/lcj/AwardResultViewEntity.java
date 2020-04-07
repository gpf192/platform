package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 * 用户与部门关联
 * @author Administrator
 *
 */
@Entity
@Table(name = "lcj_award_result_view")
@EntityListeners(AuditingEntityListener.class)
public class AwardResultViewEntity implements Serializable {
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

	@Column(name = "award_num") // 对奖个数
	private Integer awardNumber;
	
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

	public Integer getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(Integer awardNumber) {
		this.awardNumber = awardNumber;
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

	public String getPrizeCode() {
		return prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}
	
	
	
	
}
