package cn.xsdzq.platform.entity.mall;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mall_present_result")
public class PresentResultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_result_sequence")
	@SequenceGenerator(name = "present_result_sequence", sequenceName = "present_result_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "record_time", nullable = false)
	private Date recordTime;
	
	@Column(name = "integral_expense", nullable = false)
	private int integralExpense ;//消耗积分


	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "card_id", referencedColumnName = "id")
	private PresentCardEntity presentCardEntity;

	public int getIntegralExpense() {
		return integralExpense;
	}

	public void setIntegralExpense(int integralExpense) {
		this.integralExpense = integralExpense;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
	}

	public PresentCardEntity getPresentCardEntity() {
		return presentCardEntity;
	}

	public void setPresentCardEntity(PresentCardEntity presentCardEntity) {
		this.presentCardEntity = presentCardEntity;
	}

}
