package cn.xsdzq.platform.model;

import java.io.Serializable;

public class LiveRecordDTO implements Serializable{
	private long id;
	private String recordTime;
	private String clientId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "LiveRecordDTO [id=" + id + ", recordTime=" + recordTime + ", clientId=" + clientId + "]";
	}
	public LiveRecordDTO(long id, String recordTime, String clientId) {
		super();
		this.id = id;
		this.recordTime = recordTime;
		this.clientId = clientId;
	}
	public LiveRecordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
