package cn.xsdzq.platform.entity.thx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name = "thx_user_risk_info")
@EntityListeners(AuditingEntityListener.class)
public class UserRiskEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_risk_sequence")
	@SequenceGenerator(name = "user_risk_sequence", sequenceName = "sequence_user_risk", allocationSize = 1)
	@Column(name = "id")
	private long id;//主键
	
	@Column(name = "user_id")
	private String userId;//用户ID
	
	@Column(name = "username")
	private String username;//用户姓名
	
	@Column(name = "certificate")
	private String certificate;//用户身份证号码
	
	@Column(name = "mobile")
	private String mobile;//用户手机号
	
	@Column(name = "evaluation_answer")
	private String evaluationAnswer;//风险测评答案
	
	@Column(name = "evaluation_result")
	private String evaluationResult;//风险测评结果
	
	@Column(name = "evaluation_time")
	private String evaluationTime;//风测时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEvaluationAnswer() {
		return evaluationAnswer;
	}

	public void setEvaluationAnswer(String evaluationAnswer) {
		this.evaluationAnswer = evaluationAnswer;
	}

	public String getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(String evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

	public String getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(String evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	@Override
	public String toString() {
		return "UserRiskInfoEntity [id=" + id + ", userId=" + userId + ", username=" + username + ", certificate="
				+ certificate + ", mobile=" + mobile + ", evaluationAnswer=" + evaluationAnswer + ", evaluationResult="
				+ evaluationResult + ", evaluationTime=" + evaluationTime + "]";
	}
	
	
}
