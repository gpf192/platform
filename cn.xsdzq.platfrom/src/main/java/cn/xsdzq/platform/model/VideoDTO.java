package cn.xsdzq.platform.model;

public class VideoDTO {
	private long id;
	private String name;

	private String startTime;

	private String fundCode;

	private String videoUrl;

	private String content;

	private boolean toogle;

	private int pageNumber;

	private int videoPlayNumber;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getFundCode() {
		return fundCode;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public String getContent() {
		return content;
	}

	public boolean isToogle() {
		return toogle;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getVideoPlayNumber() {
		return videoPlayNumber;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setToogle(boolean toogle) {
		this.toogle = toogle;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setVideoPlayNumber(int videoPlayNumber) {
		this.videoPlayNumber = videoPlayNumber;
	}

	@Override
	public String toString() {
		return "VideoDTO [id=" + id + ", name=" + name + ", startTime=" + startTime + ", fundCode=" + fundCode
				+ ", videoUrl=" + videoUrl + ", content=" + content + ", toogle=" + toogle + ", pageNumber="
				+ pageNumber + ", videoPlayNumber=" + videoPlayNumber + "]";
	}
	
	//private Date createtime;
	
	//private Date modifytime;
	
}
