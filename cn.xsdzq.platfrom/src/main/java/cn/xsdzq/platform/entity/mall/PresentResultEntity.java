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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_result_sequence")
	@SequenceGenerator(name = "present_result_sequence", sequenceName = "present_result_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	// 兑换时使用的积分
	@Column(name = "integral_number", nullable = false)
	private int integralNumber ;//消耗积分

	// 积分金额 这个字段保留，不需要
	@Column(name = "value")
	private double value;

	@Column(name = "change_number")
	private int changeNumber;//兑换数量
	
	@Column(name = "data_flag")
	private String dateFlag; // 每日的判断标准

	@Column(name = "group_time")
	private String groupTime; // group by 计算 202009
	
	@Column(name = "record_time", nullable = false)
	private Date recordTime;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "present_id", referencedColumnName = "id")
	private PresentEntity presentEntity;

	
	public int getIntegralNumber() {
		return integralNumber;
	}

	public void setIntegralNumber(int integralNumber) {
		this.integralNumber = integralNumber;
	}

	

	public int getChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(int changeNumber) {
		this.changeNumber = changeNumber;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
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

	public PresentEntity getPresentEntity() {
		return presentEntity;
	}

	public void setPresentEntity(PresentEntity presentEntity) {
		this.presentEntity = presentEntity;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PresentResultEntity [id=" + id + ", integralNumber=" + integralNumber + ", value=" + value
				+ ", changeNumber=" + changeNumber + ", dateFlag=" + dateFlag + ", groupTime=" + groupTime
				+ ", recordTime=" + recordTime + ", mallUserEntity=" + mallUserEntity + ", presentEntity="
				+ presentEntity + "]";
	}



}
