package cn.xsdzq.platform.model.lcj;

import cn.xsdzq.platform.model.CategoryDTO;

public class DepartmentDTO  implements Comparable<DepartmentDTO> {
	private long id;
	private String code;
	private String name;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(DepartmentDTO arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
