package cn.xsdzq.platform.model.lcj;

import java.util.Date;

import javax.persistence.Column;

public class ProductDTO {
	private long id;
	private String name;
	private String code;
	private String type;
	private String coefficient;
	
	private Date beginDate;
	
	private Date endDate;	
	private String initialAmount;//起始金额
	
	private int flag;//0-场内  1-场外
	

	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(String initialAmount) {
		this.initialAmount = initialAmount;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public ProductDTO() {
		super();
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", code=" + code + ", type=" + type + ", coefficient="
				+ coefficient + ", beginDate=" + beginDate + ", endDate=" + endDate + ", initialAmount=" + initialAmount
				+ ", flag=" + flag + "]";
	}
	public ProductDTO(long id, String name, String code, String type, String coefficient, Date beginDate, Date endDate,
			String initialAmount, int flag) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.type = type;
		this.coefficient = coefficient;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.initialAmount = initialAmount;
		this.flag = flag;
	}

	
}
