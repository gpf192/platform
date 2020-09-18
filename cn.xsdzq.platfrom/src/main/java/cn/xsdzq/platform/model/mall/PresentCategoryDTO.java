package cn.xsdzq.platform.model.mall;

public class PresentCategoryDTO {

	private Long id;
	private String name;
	private String code;
	private String flag;
	private String createtime;
	private int isNew;//0新增，1更改

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
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

	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "PresentCategoryDTO [id=" + id + ", name=" + name + ", code=" + code + ", flag=" + flag + ", createtime="
				+ createtime + "]";
	}



}
