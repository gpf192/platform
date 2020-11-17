package cn.xsdzq.platform.model.mall;

import java.util.Date;


public class CRMCreditApiMsgDTO {
	private long id;
	
	private String serialNum;

	private String msg;
	
	private String recordTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	@Override
	public String toString() {
		return "CRMCreditApiMsgDTO [id=" + id + ", serialNum=" + serialNum + ", msg=" + msg + ", recordTime="
				+ recordTime + "]";
	}
	
}
