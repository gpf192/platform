package cn.xsdzq.platform.model;

public class InfoDTO {
	private long id;
	private String title;
	private Long categoryId;
	private String label;
	private String content;
	private String exp;

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

}
