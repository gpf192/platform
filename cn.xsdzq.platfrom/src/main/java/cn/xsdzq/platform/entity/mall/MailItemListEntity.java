package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_mail_list")
@EntityListeners(AuditingEntityListener.class)
public class MailItemListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "item_code", unique = true)
	private String itemCode;

	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "flag")
	private int flag;

	public String getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "MailItemListEntity [itemCode=" + itemCode + ", itemName=" + itemName + ", flag=" + flag + "]";
	}
	
	
}
