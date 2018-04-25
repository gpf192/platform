package cn.xsdzq.platform.model;

public class UserDTO {
	// 登录名称
	private long id;

	private String username;

	private String alias;

	private int level;

	private String password;

	private String phone;

	private String department;

	private String address;

	private boolean enabled;

	public UserDTO() {
		super();
	}

	public UserDTO(long id, String username, String alias, int level, String password, String phone, String department,
			String address, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.alias = alias;
		this.level = level;
		this.password = password;
		this.phone = phone;
		this.department = department;
		this.address = address;
		this.enabled = enabled;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", alias=" + alias + ", level=" + level + ", password=" + password
				+ ", phone=" + phone + ", department=" + department + ", address=" + address + ", enabled=" + enabled
				+ "]";
	}

}
