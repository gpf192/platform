package cn.xsdzq.platform.model;


public class DzhDTO {
	private long id;
	private String name;
	
	private String phone;
	
	private String activity;
	
	private String recordtime;

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

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	@Override
	public String toString() {
		return "DzhDTO [id=" + id + ", name=" + name + ", phone=" + phone + ", activity=" + activity + ", recordtime="
				+ recordtime + "]";
	}
	
}
