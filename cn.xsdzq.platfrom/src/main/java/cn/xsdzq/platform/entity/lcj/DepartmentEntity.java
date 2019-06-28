package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "lcj_department")
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique = true)
	private long id;

	@Column(name = "de_code",unique = true,length = 50)
	private String de_code;
	
	@Column(name = "de_name",length = 500)
	private String de_name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDe_code() {
		return de_code;
	}

	public void setDe_code(String de_code) {
		this.de_code = de_code;
	}

	public String getDe_name() {
		return de_name;
	}

	public void setDe_name(String de_name) {
		this.de_name = de_name;
	}

	@Override
	public String toString() {
		return "DepartmentEntity [id=" + id + ", de_code=" + de_code + ", de_name=" + de_name + "]";
	}
	
	
}
