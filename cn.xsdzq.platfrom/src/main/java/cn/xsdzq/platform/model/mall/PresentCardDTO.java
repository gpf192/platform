package cn.xsdzq.platform.model.mall;

import cn.xsdzq.platform.entity.mall.PresentEntity;

public class PresentCardDTO {
	private long id;
	private String cardId;
	private String presentName;
	private String password;
	private String cardStatus;//卡券状态， 上架1/下架0
	private String convertStatus;//兑换状态，已兑换1/未兑换0
	private long presentId;
	private PresentEntity present;//二级目录
	private String createDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getConvertStatus() {
		return convertStatus;
	}
	public void setConvertStatus(String convertStatus) {
		this.convertStatus = convertStatus;
	}
	public long getPresentId() {
		return presentId;
	}
	public void setPresentId(long presentId) {
		this.presentId = presentId;
	}
	public PresentEntity getPresent() {
		return present;
	}
	public void setPresent(PresentEntity present) {
		this.present = present;
	}
	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getPresentName() {
		return presentName;
	}
	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}
	@Override
	public String toString() {
		return "PresentCardDTO [id=" + id + ", cardId=" + cardId + ", password=" + password + ", cardStatus="
				+ cardStatus + ", convertStatus=" + convertStatus + ", presentId=" + presentId + ", present=" + present
				+ ", createDate=" + createDate + "]";
	}
	
	

}
