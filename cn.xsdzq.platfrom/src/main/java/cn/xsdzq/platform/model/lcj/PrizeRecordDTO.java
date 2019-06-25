package cn.xsdzq.platform.model.lcj;

import javax.persistence.Column;

public class PrizeRecordDTO {
	private long id;
	
	private String username;
	
	private String account;
	
	private String prizeName;
	
	private String createtime;

	public PrizeRecordDTO(long id, String username, String account, String prizeName, String createtime) {
		super();
		this.id = id;
		this.username = username;
		this.account = account;
		this.prizeName = prizeName;
		this.createtime = createtime;
	}

	public PrizeRecordDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "PrizeRecordDTO [id=" + id + ", username=" + username + ", account=" + account + ", prizeName="
				+ prizeName + ", createtime=" + createtime + "]";
	}

}
