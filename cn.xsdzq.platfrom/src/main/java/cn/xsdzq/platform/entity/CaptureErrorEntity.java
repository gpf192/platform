package cn.xsdzq.platform.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "captrue_error_info")
@EntityListeners(AuditingEntityListener.class)
// 全局错误捕获
public class CaptureErrorEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "error_info_sequence")
	@SequenceGenerator(name = "error_info_sequence", sequenceName = "sequence_error_info", allocationSize = 1)
	@Column(name = "id")
	private long id;

	// type = 1 react errorBoundnary
	@Column(name = "type")
	private int type;

	@Column(name = "device_info")
	private String deviceInfo;

	@Column(name = "erro_info", length = 500)
	private String erroInfo;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getErroInfo() {
		return erroInfo;
	}

	public void setErroInfo(String erroInfo) {
		this.erroInfo = erroInfo;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "CaptureErrorEntity [id=" + id + ", type=" + type + ", deviceInfo=" + deviceInfo + ", erroInfo="
				+ erroInfo + ", createtime=" + createtime + "]";
	}

}
