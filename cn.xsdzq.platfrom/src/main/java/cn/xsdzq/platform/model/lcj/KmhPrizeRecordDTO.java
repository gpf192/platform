package cn.xsdzq.platform.model.lcj;

import java.util.Date;

public class KmhPrizeRecordDTO {
	private Long id;
	
	private String clientId;
	
	private String clientName;
	
	private boolean type; // type 为0 表示减少 reason 以0开头 0X， 为1表示增加 reason 以1开头 1X

	private String reason;

	private Integer number = 0; // 增加或者减少的数量,默认为0
	
	private String dateFlag; // 每日的判断标准

	private String recordTime;

	private String serialNum;
	
	
	public String getClientName() {
		return clientName;
	}



	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public Long getId() {
		return id;
	}

	public String getClientId() {
		return clientId;
	}

	public boolean isType() {
		return type;
	}

	public String getReason() {
		return reason;
	}

	public Integer getNumber() {
		return number;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	

	public String getSerialNum() {
		return serialNum;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@Override
	public String toString() {
		return "KmhPrizeRecordDTO [id=" + id + ", clientId=" + clientId + ", type=" + type + ", reason=" + reason
				+ ", number=" + number + ", dateFlag=" + dateFlag + ", recordTime=" + recordTime + ", serialNum="
				+ serialNum + "]";
	}
	
}
