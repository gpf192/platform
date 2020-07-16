package cn.xsdzq.platform.model.lcj;

public class AwardResultdDTO {
	private long id;
	
	private String username;
	
	private String clientId;
	
	private String prizeName;
	private Integer awardNum;

	
	private String createtime;
	private String departName;

	

	public AwardResultdDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public AwardResultdDTO(long id, String username, String clientId, String prizeName, Integer awardNum,
			String createtime) {
		super();
		this.id = id;
		this.username = username;
		this.clientId = clientId;
		this.prizeName = prizeName;
		this.awardNum = awardNum;
		this.createtime = createtime;
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

	public Integer getAwardNum() {
		return awardNum;
	}

	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}

	public String getDepartName() {
		return departName;
	}



	public void setDepartName(String departName) {
		this.departName = departName;
	}



	@Override
	public String toString() {
		return "AwardResultdDTO [id=" + id + ", username=" + username + ", clientId=" + clientId + ", prizeName="
				+ prizeName + ", createtime=" + createtime + "]";
	}
	
}
