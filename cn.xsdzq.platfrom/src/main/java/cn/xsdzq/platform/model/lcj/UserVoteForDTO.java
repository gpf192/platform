package cn.xsdzq.platform.model.lcj;

import java.io.Serializable;

import javax.persistence.Column;

public class UserVoteForDTO implements Serializable{
	 private Long id;
		
	 // 客户号
	 private String clientId;
	 
	 //用户姓名
	 private String username;
	 
	 //投票时间
	 private String voteTime;
	 
	 //使用对象-员工
	 private String empName;
	 
	 //员工编号
	 private String empCode;
	 
	 //使用票数
	 private String voteNum;
	 
	 //隶属营业部
	 private String salesDepartment;
	 
	 //隶属赛区
	 private String division;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(String voteTime) {
		this.voteTime = voteTime;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getSalesDepartment() {
		return salesDepartment;
	}

	public void setSalesDepartment(String salesDepartment) {
		this.salesDepartment = salesDepartment;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "UserVoteForDTO [id=" + id + ", clientId=" + clientId + ", username=" + username + ", voteTime="
				+ voteTime + ", empName=" + empName + ", empCode=" + empCode + ", voteNum=" + voteNum
				+ ", salesDepartment=" + salesDepartment + ", division=" + division + "]";
	}
	 
	 
}
