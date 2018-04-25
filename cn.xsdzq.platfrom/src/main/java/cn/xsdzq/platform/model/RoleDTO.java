package cn.xsdzq.platform.model;

public class RoleDTO {

	private long id;

	private String name;

	private Long parent_id;// 父级角色ID

	private String describe;// 描述

	private int sort;// 排序序号

	public RoleDTO() {
		super();
	}

	public RoleDTO(long id, String name, Long parent_id, String describe, int sort) {
		super();
		this.id = id;
		this.name = name;
		this.parent_id = parent_id;
		this.describe = describe;
		this.sort = sort;
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

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
