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

@Entity
@Table(name = "lcj_employee_info")
@EntityListeners(AuditingEntityListener.class)
public class EmpEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
	@SequenceGenerator(name = "employee_sequence", sequenceName = "sequence_employee", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "emp_name")
	private String emp_name;
	
	@Column(name = "emp_code")
	private String emp_code;
	
	@Column(name = "emp_type")
	private String emp_type;//在编性质
	
	@Column(name = "emp_category")
	private String emp_category;//人员类别
	
	@Column(name = "contract")
	private String contract;//签约合同
	
	@Column(name = "entry_time")
	private String entry_time;//入职时间
	
	@Column(name = "sales_department")
	private String sales_department;//隶属营业部
	
	@Column(name = "division")
	private String division;//隶属赛区

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

	public String getEmp_type() {
		return emp_type;
	}

	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}

	public String getEmp_category() {
		return emp_category;
	}

	public void setEmp_category(String emp_category) {
		this.emp_category = emp_category;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
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
		return "EmpEntity [id=" + id + ", emp_name=" + emp_name + ", emp_code=" + emp_code + ", emp_type=" + emp_type
				+ ", emp_category=" + emp_category + ", contract=" + contract + ", entry_time=" + entry_time
				+ ", sales_department=" + sales_department + ", division=" + division + "]";
	}
	
	
}
