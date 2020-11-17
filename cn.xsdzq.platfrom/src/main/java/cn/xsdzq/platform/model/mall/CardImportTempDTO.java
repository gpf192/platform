package cn.xsdzq.platform.model.mall;


public class CardImportTempDTO {
	private long id;

	private String cardId;

	private String password;

	private int cardStatus = 1 ;//卡券状态， 上架1/下架0

	private int convertStatus = 0;//兑换状态，已兑换1/未兑换0
	
	private String presentCode;

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

	public int getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}

	public int getConvertStatus() {
		return convertStatus;
	}

	public void setConvertStatus(int convertStatus) {
		this.convertStatus = convertStatus;
	}

	public String getPresentCode() {
		return presentCode;
	}

	public void setPresentCode(String presentCode) {
		this.presentCode = presentCode;
	}

	@Override
	public String toString() {
		return "CardImportTempDTO [id=" + id + ", cardId=" + cardId + ", password=" + password + ", cardStatus="
				+ cardStatus + ", convertStatus=" + convertStatus + ", presentCode=" + presentCode + "]";
	}
	
	
}
