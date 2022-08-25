package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_user_info")
@EntityListeners(AuditingEntityListener.class)
public class MallUserInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_user_info_sequence")
	@SequenceGenerator(name = "mall_user_info_sequence", sequenceName = "mall_user_info_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	// 当前总积分
	@Column(name = "credit_score")
	private int creditScore = 0;

	// 会员等级使用 历史总积分
	@Column(name = "sum_score")
	private int sumScore = 0;

	// 会员等级使用
	@Column(name = "user_level")
	private int userLevel = 0;
	
	@Column(name = "client_id", insertable = false, updatable = false)
	private String clientId ;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;

	// 冻结积分
	@Column(name = "frozen_integral",columnDefinition = "int default 0")
	private Integer frozenIntegral = 0;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCreditScore() {
		return creditScore - frozenIntegral;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
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

	public Integer getFrozenIntegral() {
		return frozenIntegral;
	}

	public void setFrozenIntegral(Integer frozenIntegral) {
		this.frozenIntegral = frozenIntegral;
	}

	@Override
	public String toString() {
		return "MallUserInfoEntity{" +
				"id=" + id +
				", creditScore=" + creditScore +
				", sumScore=" + sumScore +
				", userLevel=" + userLevel +
				", clientId='" + clientId + '\'' +
				", mallUserEntity=" + mallUserEntity +
				", createtime=" + createtime +
				", modifytime=" + modifytime +
				", frozenIntegral=" + frozenIntegral +
				'}';
	}
}
