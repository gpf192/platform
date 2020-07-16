package cn.xsdzq.platform.model.lcj;

public class PrizeRecordDTO {
	private long id;
	
	private String username;
	
	private String clientId;
	
	private String prizeName;
	
	private String createtime;

	private String departName;
	private String prizeCode;
	

	public PrizeRecordDTO(long id, String username, String clientId, String prizeName, String createtime,
			String departName) {
		super();
		this.id = id;
		this.username = username;
		this.clientId = clientId;
		this.prizeName = prizeName;
		this.createtime = createtime;
		this.departName = departName;
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



	public String getPrizeCode() {
		return prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Override
	public String toString() {
		return "PrizeRecordDTO [id=" + id + ", username=" + username + ", clientId=" + clientId + ", prizeName="
				+ prizeName + ", createtime=" + createtime + "]";
	}

	
}
