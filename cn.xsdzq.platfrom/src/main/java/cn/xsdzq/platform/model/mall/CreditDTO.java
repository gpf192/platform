package cn.xsdzq.platform.model.mall;


public class CreditDTO {
	private long id;
	
	private String categoryName;//项目名称
	
	private String categoryCode;//项目编码，唯一
	
	private String frontName;//前端显示名称
	
	private String integralValue;//积分值
	
	private String flag;//是否启用,0未启用，1启用

	private String createtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getFrontName() {
		return frontName;
	}

	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}

	public String getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(String integralValue) {
		this.integralValue = integralValue;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "CreditDTO [id=" + id + ", categoryName=" + categoryName + ", categoryCode=" + categoryCode
				+ ", frontName=" + frontName + ", integralValue=" + integralValue + ", flag=" + flag + ", createtime="
				+ createtime + "]";
	}
	
	
}
