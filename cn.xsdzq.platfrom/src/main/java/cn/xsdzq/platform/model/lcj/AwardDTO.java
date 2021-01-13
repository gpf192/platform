package cn.xsdzq.platform.model.lcj;

import java.io.Serializable;

import javax.persistence.Column;

public class AwardDTO implements Serializable{
	private long id;

	private String awardName;// 奖品名称 牛气冲天奖

	private String awardNameAlias;// 奖品别名 京东E卡，价值50元

	private String imageName;

	private Integer index;

	private Integer imageNumber;

	private Integer awardValue;
	private Integer amount;
	private Integer usedNumber;

	public Integer getAmount() {
		return amount;
	}

	public Integer getUsedNumber() {
		return usedNumber;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setUsedNumber(Integer usedNumber) {
		this.usedNumber = usedNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardNameAlias() {
		return awardNameAlias;
	}

	public void setAwardNameAlias(String awardNameAlias) {
		this.awardNameAlias = awardNameAlias;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(Integer imageNumber) {
		this.imageNumber = imageNumber;
	}

	public Integer getAwardValue() {
		return awardValue;
	}

	public void setAwardValue(Integer awardValue) {
		this.awardValue = awardValue;
	}

	@Override
	public String toString() {
		return "AwardDTO [id=" + id + ", awardName=" + awardName + ", awardNameAlias=" + awardNameAlias + ", imageName="
				+ imageName + ", index=" + index + ", imageNumber=" + imageNumber + ", awardValue=" + awardValue
				+ ", amount=" + amount + ", usedNumber=" + usedNumber + "]";
	}
	
	
}
