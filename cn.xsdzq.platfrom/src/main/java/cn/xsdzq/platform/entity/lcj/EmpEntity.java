package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "lcj_employee_info")
@EntityListeners(AuditingEntityListener.class)
public class EmpEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
	@SequenceGenerator(name = "employee_sequence", sequenceName = "sequence_employee", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "emp_id", unique = true, nullable = false)
	private String empId;

	@Column(name = "emp_name")
	private String empName;// 人员姓名

	@Column(name = "emp_code", unique = true, nullable = false)
	private String empCode;// 人员编号

	@Column(name = "emp_type")
	private String empType;// 在编性质

	@Column(name = "emp_category")
	private String empCategory;// 人员类别

	@Column(name = "contract")
	private String contract;// 签约合同

	@Column(name = "entry_time")
	private String entryTime;// 入职时间


	@Column(name = "division")
	private String division;// 隶属赛区 0-新手  1-王者

	@Column(name = "department_code", insertable = false, updatable = false)
	private String departmentCode;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "department_code", referencedColumnName = "code")
	private DepartmentEntity departmentEntity;
	
	@Column(name = "enable", columnDefinition = "int default 1") // 中奖人数
	private int enable = 1;//默认账户开启，1-开启   2-关闭
	

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}



	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

	@Override
	public String toString() {
		return "EmpEntity [id=" + id + ", empId=" + empId + ", empName=" + empName + ", empCode=" + empCode
				+ ", empType=" + empType + ", empCategory=" + empCategory + ", contract=" + contract + ", entryTime="
				+ entryTime + ", division=" + division + ", departmentCode=" + departmentCode + ", departmentEntity="
				+ departmentEntity + ", enable=" + enable + "]";
	}


}
