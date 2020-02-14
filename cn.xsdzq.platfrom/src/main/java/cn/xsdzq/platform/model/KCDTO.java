package cn.xsdzq.platform.model;

public class KCDTO {
	private long id;
	private String name;
	private String clientId;
	private String phone;
	private String createtime;
	
	public KCDTO() {

	}

	

	public KCDTO(long id, String name, String clientId, String phone, String createtime) {
		super();
		this.id = id;
		this.name = name;
		this.clientId = clientId;
		this.phone = phone;
		this.createtime = createtime;
	}



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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	
	public String getClientId() {
		return clientId;
	}



	public void setClientId(String clientId) {
		this.clientId = clientId;
	}



	@Override
	public String toString() {
		return "KCDTO [id=" + id + ", name=" + name + ", phone=" + phone + ", createtime=" + createtime + "]";
	}
	
}
