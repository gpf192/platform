package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "record")
public class RecordEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordEntity() {
	}

	public RecordEntity(long id, String name, String channel, String ip, String uri, String agent, String path,
			int acount, Date visitTime, Date modifytime) {
		super();
		this.id = id;
		this.name = name;
		this.channel = channel;
		this.ip = ip;
		this.uri = uri;
		this.agent = agent;
		this.path = path;
		this.acount = acount;
		this.visitTime = visitTime;
		this.modifytime = modifytime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, length = 20)
	private long id;

	@Column(name = "name", nullable = true, length = 50)
	private String name;

	@Column(name = "channel", nullable = true, length = 50)
	private String channel;

	@Column(name = "ip", nullable = true, length = 16)
	private String ip;

	@Column(name = "uri", nullable = false, length = 100)
	private String uri;

	@Column(name = "agent", nullable = true, length = 200)
	private String agent;

	@Column(name = "path", nullable = true, length = 100)
	private String path;

	@Column(name = "acount", nullable = true)
	private int acount;

	@CreatedDate
	@Column(name = "visitTime")
	private Date visitTime;

	@LastModifiedDate
	@Column(name = "modifytime", nullable = true)
	private Date modifytime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}
