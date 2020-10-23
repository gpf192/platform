package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_crm_api_err_msg")
@EntityListeners(AuditingEntityListener.class)
public class CRMCreditApiErrorMsgEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_crm_err_msg_sequence")
	@SequenceGenerator(name = "mall_crm_err_msg_sequence", sequenceName = "sequence_mall_crm_err_msg", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "serial_num")
	private String serialNum;

	@Column(name = "msg")
	private String msg;
	
	@Column(name = "record_time")
	private Date recordTime;

	@Column(name = "sta")
	private int sta;//0-未处理记录，   1-已处理
	
	@Column(name = "modify_time")
	private Date modifyTime;
	
	@Column(name = "modifyBy")
	private String modifyBy;
	
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public int getSta() {
		return sta;
	}

	public void setSta(int sta) {
		this.sta = sta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	@Override
	public String toString() {
		return "CRMCreditApiErrorMsgEntity [id=" + id + ", serialNum=" + serialNum + ", msg=" + msg + ", recordTime="
				+ recordTime + ", sta=" + sta + ", modifyTime=" + modifyTime + ", modifyBy=" + modifyBy + "]";
	}
	
}

