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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj_user")
@EntityListeners(AuditingEntityListener.class)
public class LcjUserEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, length = 20)
	private Long id;

	//lcj_user
	// 客户号
	@Column(name = "client_id", unique = true, nullable = false, length = 100)
	private String clientId;
	
	// 客户姓名
	@Column(name = "client_name", unique = true, length = 100)
	private String clientName;

	// 资金账号
	@Column(name = "fund_account", unique = true, length = 100)
	private String fundAccount;

	@Column(name = "access_token", nullable = true, length = 100)
	private String accessToken;

	@Column(name = "password", nullable = true, length = 500)
	private String password;

	@Column(name = "mobile",  nullable = true, length = 12)
	private String mobile;

	@Column(name = "app_version", nullable = true, length = 100)
	private String appVersion;

	@Column(name = "last_op_ip", nullable = true, length = 200)
	private String lastOpIP;

	@Column(name = "last_login_time", nullable = true, length = 200)
	private String lastLoginTime;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;

	public LcjUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LcjUserEntity(Long id, String clientId, String fundAccount, String accessToken, String password, String mobile,
			String appVersion, String lastOpIP, String lastLoginTime, Date createtime, Date modifytime) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.fundAccount = fundAccount;
		this.accessToken = accessToken;
		this.password = password;
		this.mobile = mobile;
		this.appVersion = appVersion;
		this.lastOpIP = lastOpIP;
		this.lastLoginTime = lastLoginTime;
		this.createtime = createtime;
		this.modifytime = modifytime;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getLastOpIP() {
		return lastOpIP;
	}

	public void setLastOpIP(String lastOpIP) {
		this.lastOpIP = lastOpIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", clientId=" + clientId + ", fundAccount=" + fundAccount + ", accessToken="
				+ accessToken + ", password=" + password + ", mobile=" + mobile + ", appVersion=" + appVersion
				+ ", lastOpIP=" + lastOpIP + ", lastLoginTime=" + lastLoginTime + ", createtime=" + createtime
				+ ", modifytime=" + modifytime + "]";
	}
/*
	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lcj_user_sequence")
	@SequenceGenerator(name = "lcj_user_sequence", sequenceName = "sequence_lcj_user", allocationSize = 1)
	@Column(name = "id")
	 private Long id;

	 // 客户号
	 @Column(name = "client_id", unique = true, nullable = false, length = 100)
	 private String clientId;
	 
	 //用户姓名
	 @Column(name = "username")
	 private String username;
	
	 // 资金账号
	 @Column(name = "fund_account", unique = true, length = 100)
	 private String fundAccount;

	 @Column(name = "access_token", nullable = true, length = 100)
	 private String accessToken;

	 @Column(name = "password", nullable = true, length = 500)
	 private String password;

	 @Column(name = "mobile", unique = true, nullable = true, length = 12)
	 private String mobile;

	 @Column(name = "app_version", nullable = true, length = 100)
	 private String appVersion;

	 @Column(name = "last_op_ip", nullable = true, length = 200)
	 private String lastOpIP;

	 @Column(name = "last_login_time", nullable = true, length = 200)
	 private String lastLoginTime;

	 //投顾id
	 @Column(name = "invest_id")
	 private String investId;
	 
	 //投顾姓名
	 @Column(name = "invest_name")
	 private String investName;
	 
	 //经纪人id
	 @Column(name = "broker_id")
	 private String brokerId;
	 
	 //经纪人姓名
	 @Column(name = "broker_name")
	 private String brokerName;
	 
	 // 创建时间
	 @Column(name = "createtime")
	 @CreatedDate
	 private Date createtime;

	 // 修改时间
	 @Column(name = "modifytime", nullable = true)
	 @LastModifiedDate
	 private Date modifytime;
	 //关联票数id
	 @Column(name = "ticketId", insertable = false, updatable = false)
	 private long ticketId;
	
	 @OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	 @JoinColumn(name = "ticketId", referencedColumnName = "id")
	 private TicketEntity ticketEntity;
		

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public TicketEntity getTicketEntity() {
		return ticketEntity;
	}

	public void setTicketEntity(TicketEntity ticketEntity) {
		this.ticketEntity = ticketEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getLastOpIP() {
		return lastOpIP;
	}

	public void setLastOpIP(String lastOpIP) {
		this.lastOpIP = lastOpIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getInvestName() {
		return investName;
	}

	public void setInvestName(String investName) {
		this.investName = investName;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public LcjUserEntity() {
		super();
	}

	

	public LcjUserEntity(Long id, String clientId, String username, String fundAccount, String accessToken,
			String password, String mobile, String appVersion, String lastOpIP, String lastLoginTime, String investId,
			String investName, String brokerId, String brokerName, Date createtime, Date modifytime, long ticketId,
			TicketEntity ticketEntity) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.username = username;
		this.fundAccount = fundAccount;
		this.accessToken = accessToken;
		this.password = password;
		this.mobile = mobile;
		this.appVersion = appVersion;
		this.lastOpIP = lastOpIP;
		this.lastLoginTime = lastLoginTime;
		this.investId = investId;
		this.investName = investName;
		this.brokerId = brokerId;
		this.brokerName = brokerName;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.ticketId = ticketId;
		this.ticketEntity = ticketEntity;
	}

	@Override
	public String toString() {
		return "LcjUserEntity [id=" + id + ", clientId=" + clientId + ", username=" + username + ", fundAccount="
				+ fundAccount + ", accessToken=" + accessToken + ", password=" + password + ", mobile=" + mobile
				+ ", appVersion=" + appVersion + ", lastOpIP=" + lastOpIP + ", lastLoginTime=" + lastLoginTime
				+ ", investId=" + investId + ", investName=" + investName + ", brokerId=" + brokerId + ", brokerName="
				+ brokerName + ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}*/
	 
	 
}
