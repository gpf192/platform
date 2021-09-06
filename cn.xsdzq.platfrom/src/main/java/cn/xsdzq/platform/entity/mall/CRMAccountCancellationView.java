package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_crm_acc_cancel_view")
@EntityListeners(AuditingEntityListener.class)
public class CRMAccountCancellationView implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	
	@Column(name = "client_id")
	private String clientId;//客户编号
	
	@Column(name = "client_name")
	private String clientName;//客户姓名
	
	@Column(name = "acc_cancel_Date")
	private int xhDate;//销户日期

	public String getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public int getXhDate() {
		return xhDate;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setXhDate(int xhDate) {
		this.xhDate = xhDate;
	}

	@Override
	public String toString() {
		return "CRMAccountCancellationView [clientId=" + clientId + ", clientName=" + clientName + ", xhDate=" + xhDate
				+ "]";
	}
	
	
}
