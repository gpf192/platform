package cn.xsdzq.platform.entity.lcj;

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
@Table(name = "lcj_prize_record")
@EntityListeners(AuditingEntityListener.class)
public class PrizeRecordEntity {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prize_record_sequence")
	@SequenceGenerator(name = "prize_record_sequence", sequenceName = "sequence_prize_record", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "account")
	private String account;//资金账号
	
	@Column(name = "client_id")
	private String clientId;//客户号
	
	@Column(name = "prize_id")
	private String prizeId;
	
	@Column(name = "prizeName")
	private String prizeName;
	
	@Column(name = "createtime")
	private Date createtime;

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PrizeRecord [id=" + id + ", username=" + username + ", account=" + account + ", prizeName=" + prizeName
				+ ", createtime=" + createtime + "]";
	}

	

	
	

}
