package cn.xsdzq.platform.model.mall;

public class PresentCategory {

	private Long id;
	private String name;
	private String flag;

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

	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PresentCategory [id=" + id + ", name=" + name + ", flag=" + flag + "]";
	}

}
