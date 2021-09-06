package cn.xsdzq.platform.model.mall;

public class PresentDTO {
	private long id;
	private String name;
	private String code;
	private long categoryId;
	
	private String categoryName;
	
	private float faceValue;//面值	
	private float value;//实际价格
	private String image;
	private String bigImage;
	private boolean isHot;
	private String tip;
	private int sort;
	 private String explain;//使用说明
	private String description;	
	private int storeNumber;//总库存	
	private int convertNumber;//已兑换	
	private int storeUnused;//剩余库存
	private String status;//状态，上上架/下架
	private String createtime;
	private String attention;//attention注意事项
	private int newFlag;//0-新增，1-更新 
	private int illegalNum;//下架的卡券数量
	
	public int getIllegalNum() {
		return illegalNum;
	}
	public void setIllegalNum(int illegalNum) {
		this.illegalNum = illegalNum;
	}
	public int getNewFlag() {
		return newFlag;
	}
	public void setNewFlag(int newFlag) {
		this.newFlag = newFlag;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBigImage() {
		return bigImage;
	}
	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}
	public boolean getIsHot() {
		return isHot;
	}
	public void setIsHot(boolean isHot) {
		this.isHot = isHot;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@Override
	public String toString() {
		return "PresentDTO [id=" + id + ", name=" + name + ", code=" + code + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", faceValue=" + faceValue + ", value=" + value + ", image="
				+ image + ", bigImage=" + bigImage + ", isHot=" + isHot + ", tip=" + tip + ", sort=" + sort
				+ ", explain=" + explain + ", description=" + description + ", storeNumber=" + storeNumber
				+ ", convertNumber=" + convertNumber + ", storeUnused=" + storeUnused + ", status=" + status
				+ ", createtime=" + createtime + ", attention=" + attention + ", newFlag=" + newFlag + "]";
	}
	
}
