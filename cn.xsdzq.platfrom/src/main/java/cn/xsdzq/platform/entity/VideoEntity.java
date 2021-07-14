package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 基金视频类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "help_fund_video")
@EntityListeners(AuditingEntityListener.class)

public class VideoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_vido_sequence")
	@SequenceGenerator(name = "fund_vido_sequence", sequenceName = "sequence_fund_vido", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "name") // 每期视频名称
	private String name;

	@Column(name = "start_time") // 新建视频时间
	private String startTime;

	@Column(name = "fund_code", length = 10) // 基金视频中对应基金代码，供前端跳转使用
	private String fundCode;

	@Column(name = "viewo_url") // 视频对应的播放地址
	private String videoUrl;
	
	@Lob
	@Column(name = "content", length = 3000)
	private String content;

	@Column(name = "toogle") // 有且只有一个为true
	private boolean toogle;

	@Column(name = "page_number") // 页面访问量
	private int pageNumber;

	@Column(name = "video_play_number") // 视频的播放量
	private int videoPlayNumber;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isToogle() {
		return toogle;
	}

	public void setToogle(boolean toogle) {
		this.toogle = toogle;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getVideoPlayNumber() {
		return videoPlayNumber;
	}

	public void setVideoPlayNumber(int videoPlayNumber) {
		this.videoPlayNumber = videoPlayNumber;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "VideoEntity [id=" + id + ", name=" + name + ", startTime=" + startTime + ", fundCode=" + fundCode
				+ ", videoUrl=" + videoUrl + ", content=" + content + ", toogle=" + toogle + ", pageNumber="
				+ pageNumber + ", videoPlayNumber=" + videoPlayNumber + ", createtime=" + createtime + ", modifytime="
				+ modifytime + "]";
	}

}
