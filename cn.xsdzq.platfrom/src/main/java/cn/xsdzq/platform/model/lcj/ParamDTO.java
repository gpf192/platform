package cn.xsdzq.platform.model.lcj;

import javax.persistence.Column;

public class ParamDTO {
	private long id;
	private String title;//参数描述	
	private String code;//参数code	
	private String value;//参数值
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ParamDTO [id=" + id + ", title=" + title + ", code=" + code + ", value=" + value + "]";
	}
	
}
