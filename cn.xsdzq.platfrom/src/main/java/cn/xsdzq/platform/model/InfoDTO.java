package cn.xsdzq.platform.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class InfoDTO {
	private long id;

	@NotBlank(message = "标题不能为空")
	private String title;

	private Long categoryId;

	private String label;

	@NotBlank(message = "信息内容不能为空")
	private String content;
	private String exp;
	private String flag;// 保存还是提交
	private String checked_result;// 审核状态
	private int weight;
	private int pageView;
	private String createdBy;
	private String modifytime;
	private String categoryTitle;
	private String commonFlag;
	private String createtime;
	public InfoDTO() {

	}

	public InfoDTO(long id, String title, Long categoryId, String label, String content, String exp) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.label = label;
		this.content = content;
		this.exp = exp;
	}

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getChecked_result() {
		return checked_result;
	}

	public void setChecked_result(String checked_result) {
		this.checked_result = checked_result;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPageView() {
		return pageView;
	}

	public void setPageView(int pageView) {
		this.pageView = pageView;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCommonFlag() {
		return commonFlag;
	}

	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
