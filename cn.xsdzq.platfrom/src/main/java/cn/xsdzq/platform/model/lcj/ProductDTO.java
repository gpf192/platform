package cn.xsdzq.platform.model.lcj;

public class ProductDTO {
	private long id;
	private String name;
	private String code;
	private String type;
	private String begin_date;
	private String coefficient;
	public ProductDTO(long id, String name, String code, String type, String begin_date, String coefficient) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.type = type;
		this.begin_date = begin_date;
		this.coefficient = coefficient;
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
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", code=" + code + ", type=" + type + ", begin_date="
				+ begin_date + ", coefficient=" + coefficient + "]";
	}
	
	
}
