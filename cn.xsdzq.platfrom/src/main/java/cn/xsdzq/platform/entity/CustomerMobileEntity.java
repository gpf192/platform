package cn.xsdzq.platform.entity;

import java.io.Serializable;
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
import cn.xsdzq.platform.entity.lcj.LcjUserEntity;

/**
 * 科创预约用户,两融期权预约开户，同实体类 PageEventEntity
 * @author Administrator
 *
 */

/*
@Entity
@Table(name = "kc_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone" }) })
@EntityListeners(AuditingEntityListener.class)
public class CustomerMobileEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kc_sequence")
	@SequenceGenerator(name = "kc_sequence", sequenceName = "sequence_kc", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "CustomerMobileEntity [name=" + name + ", phone=" + phone + "]";
	}*/


@Entity
@Table(name = "lcj_page_event")
public class CustomerMobileEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "page_event_sequence")
	@SequenceGenerator(name = "page_event_sequence", sequenceName = "sequence_page_event", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "page_event_id")
	private String pageEventId;//1-两融，2-期权

	@Column(name = "action_event")
	private String actionEvent;

	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private LcjUserEntity userEntity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPageEventId() {
		return pageEventId;
	}

	public void setPageEventId(String pageEventId) {
		this.pageEventId = pageEventId;
	}

	public String getActionEvent() {
		return actionEvent;
	}

	public void setActionEvent(String actionEvent) {
		this.actionEvent = actionEvent;
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
