package cn.xsdzq.platform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 科创预约用户
 * @author Administrator
 *
 */
@Entity
@Table(name = "kc_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone" }) })
@EntityListeners(AuditingEntityListener.class)
public class CustomerMobileEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kc_sequence")
	@SequenceGenerator(name = "kc_sequence", sequenceName = "sequence_kc", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "CustomerMobileEntity [name=" + name + ", phone=" + phone + "]";
	}
	
	
	
}
