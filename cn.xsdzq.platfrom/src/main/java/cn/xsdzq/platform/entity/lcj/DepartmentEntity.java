package cn.xsdzq.platform.entity.lcj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.xsdzq.platform.entity.InfoEntity;
@Entity
@Table(name = "lcj_department_view")
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "code",unique = true,length = 50)
	private String code;
	
	@Column(name = "name",length = 500)
	private String name;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
}
