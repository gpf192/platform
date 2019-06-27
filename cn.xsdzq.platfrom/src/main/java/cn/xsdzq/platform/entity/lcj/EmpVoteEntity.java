package cn.xsdzq.platform.entity.lcj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//参赛者得票数
@Entity
@Table(name = "lcj_emp_vote_record")
@EntityListeners(AuditingEntityListener.class)
public class EmpVoteEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_vote_sequence")
	@SequenceGenerator(name = "emp_vote_sequence", sequenceName = "sequence_emp_vote_vote", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "emp_name")
	private String emp_name;
	
	@Column(name = "emp_code")
	private String emp_code;
	
	@Column(name = "sales_department")
	private String sales_department;//隶属营业部
	
	@Column(name = "division")
	private String division;//隶属赛区
	
	@Column(name = "get_vote_amount")
	private String get_vote_amount;//获得票数
	
	@Column(name = "get_vote_time")
	private String get_vote_time;//获得票时间
	
	@Column(name = "vote_from_user")
	private String vote_from_user;//来源用户

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

	public String getGet_vote_amount() {
		return get_vote_amount;
	}

	public void setGet_vote_amount(String get_vote_amount) {
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

	@Override
	public String toString() {
		return "EmpVoteEntity [id=" + id + ", emp_name=" + emp_name + ", emp_code=" + emp_code + ", sales_department="
				+ sales_department + ", division=" + division + ", get_vote_amount=" + get_vote_amount
				+ ", get_vote_time=" + get_vote_time + ", vote_from_user=" + vote_from_user + "]";
	}
	
	
	
}
