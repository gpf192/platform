package cn.xsdzq.platform.model.lcj;

import javax.persistence.Column;

public class UserVoteDTO {
	private long id;
	
	private String username;
	
	private String account;//资金账号
	
	private String total_votes;//所得总票数
	
	private String votes_source;//得票来源
	
	private String gain_time;//得票时间
	
	private String vote_for;//投票对象
	
	private String vote_for_amount;
	
	private String sales_department;//隶属营业部
	
	private String division;//隶属赛区
	private String sourceId;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTotal_votes() {
		return total_votes;
	}

	public void setTotal_votes(String total_votes) {
		this.total_votes = total_votes;
	}

	public String getVotes_source() {
		return votes_source;
	}

	public void setVotes_source(String votes_source) {
		this.votes_source = votes_source;
	}

	public String getGain_time() {
		return gain_time;
	}

	public void setGain_time(String gain_time) {
		this.gain_time = gain_time;
	}

	public String getVote_for() {
		return vote_for;
	}

	public void setVote_for(String vote_for) {
		this.vote_for = vote_for;
	}

	public String getVote_for_amount() {
		return vote_for_amount;
	}

	public void setVote_for_amount(String vote_for_amount) {
		this.vote_for_amount = vote_for_amount;
	}

	public String getSales_department() {
		return sales_department;
	}

	public void setSales_department(String sales_department) {
		this.sales_department = sales_department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "UserVoteDTO [id=" + id + ", username=" + username + ", account=" + account + ", total_votes="
				+ total_votes + ", votes_source=" + votes_source + ", gain_time=" + gain_time + ", vote_for=" + vote_for
				+ ", vote_for_amount=" + vote_for_amount + ", sales_department=" + sales_department + ", division="
				+ division + "]";
	}
	
	
}
