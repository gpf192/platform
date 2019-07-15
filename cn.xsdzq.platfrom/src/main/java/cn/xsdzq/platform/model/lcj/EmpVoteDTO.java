package cn.xsdzq.platform.model.lcj;

import java.util.Date;

import javax.persistence.Column;

public class EmpVoteDTO {
	private long id;
	
	private String emp_name;
	private String emp_code;
	
	private String sales_department;//隶属营业部
	
	private String division;//隶属赛区
	
	private int get_vote_amount;//获得票数
	
	private String get_vote_time;//获得票时间
	
	private String vote_from_user;//来源用户
	
	private int weight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
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



	public int getGet_vote_amount() {
		return get_vote_amount;
	}

	public void setGet_vote_amount(int get_vote_amount) {
		this.get_vote_amount = get_vote_amount;
	}


	public String getGet_vote_time() {
		return get_vote_time;
	}

	public void setGet_vote_time(String get_vote_time) {
		this.get_vote_time = get_vote_time;
	}

	public String getVote_from_user() {
		return vote_from_user;
	}

	public void setVote_from_user(String vote_from_user) {
		this.vote_from_user = vote_from_user;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EmpVoteDTO [id=" + id + ", emp_name=" + emp_name + ", emp_code=" + emp_code + ", sales_department="
				+ sales_department + ", division=" + division + ", get_vote_amount=" + get_vote_amount
				+ ", get_vote_time=" + get_vote_time + ", vote_from_user=" + vote_from_user + ", weight=" + weight
				+ "]";
	}


	
	
}
