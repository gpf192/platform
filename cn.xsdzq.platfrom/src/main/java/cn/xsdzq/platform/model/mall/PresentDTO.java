package cn.xsdzq.platform.model.mall;

public class PresentDTO {
	private long id;
	private String name;
	private String code;

	private String presentImage;

	private long categoryId;
	private String categoryName;
	private float faceValue;//面值	
	private float value;//实际价格
	private String description;	
	private int storeNumber;//总库存	
	private int convertNumber;//已兑换	
	private int storeUnused;//剩余库存
	private String status;//状态，上上架/下架
	private String createtime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
	public float getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(float faceValue) {
		this.faceValue = faceValue;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}
	public int getConvertNumber() {
		return convertNumber;
	}
	public void setConvertNumber(int convertNumber) {
		this.convertNumber = convertNumber;
	}
	public int getStoreUnused() {
		return storeUnused;
	}
	public void setStoreUnused(int storeUnused) {
		this.storeUnused = storeUnused;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPresentImage() {
		return presentImage;
	}
	public void setPresentImage(String presentImage) {
		this.presentImage = presentImage;
	}
	@Override
	public String toString() {
		return "PresentDTO [id=" + id + ", name=" + name + ", code=" + code + ", presentImage=" + presentImage
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", faceValue=" + faceValue
				+ ", value=" + value + ", description=" + description + ", storeNumber=" + storeNumber
				+ ", convertNumber=" + convertNumber + ", storeUnused=" + storeUnused + ", status=" + status
				+ ", createtime=" + createtime + "]";
	}
	

	
}
