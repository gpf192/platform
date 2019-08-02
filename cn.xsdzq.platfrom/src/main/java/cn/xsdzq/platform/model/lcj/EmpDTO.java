package cn.xsdzq.platform.model.lcj;

import javax.persistence.Column;

public class EmpDTO {
	private long id;

	private String emp_name;
	
	private String emp_code;
	
	private String emp_type;//在编性质
	
	private String emp_category;//人员类别
	
	private String contract;//签约合同
	
	private String entry_time;//入职时间
	
	private String departmentCode;
		
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

	

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}


	public EmpDTO() {
		super();
	}

	@Override
	public String toString() {
		return "EmpDTO [id=" + id + ", emp_name=" + emp_name + ", emp_code=" + emp_code + ", emp_type=" + emp_type
				+ ", emp_category=" + emp_category + ", contract=" + contract + ", entry_time=" + entry_time
				+ ", departmentCode=" + departmentCode + ", division=" + division + "]";
	}
	
}
