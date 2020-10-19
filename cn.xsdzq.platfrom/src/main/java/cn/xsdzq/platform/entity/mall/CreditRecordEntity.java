package cn.xsdzq.platform.entity.mall;

import java.io.Serializable;
import java.util.Date;

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

/**
 * 积分明细导入记录
 * @author ThinkPad-T430
 *注：
 */
@Entity
@Table(name = "mall_credit_record")
@EntityListeners(AuditingEntityListener.class)
public class CreditRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_record_sequence")
	@SequenceGenerator(name = "credit_record_sequence", sequenceName = "sequence_credit_record", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "type", nullable = false)
	private boolean type; // type 为0 查找减少原因 reason，type为1查找 item itemCode

	@Column(name = "reason")
	private String reason;//减票原因

	@Column(name = "reason_code")
	private String reasonCode;
	
	// 项目名称，
	@Column(name = "import_Item")
	private String importItem;	//导入时关联的项目名称
	
	// 项目名称，
	@Column(name = "import_mobile")
	private String importMobile;	//导入时的手机号
		
	// 项目名称，
	@Column(name = "item")
	private String item;//前端展示

		// 项目代码，此处不与项目类关联， 保持导入记录明细，避免项目类被删除后，找不到记录
	@Column(name = "item_code")
	private String itemCode;
	
	// 积分数
	@Column(name = "integral_number")
	private int integralNumber;
	

	// 积分金额 这个字段保留，不需要
	@Column(name = "value")
	private double value;
	
	@Column(name = "data_flag")
	private String dateFlag; // 每日的判断标准
	
	@Column(name = "group_time")
	private String groupTime; // group by 计算 202009
	
	@Column(name = "begin_date")
	private String beginDate;//生效日期
	
	@Column(name = "end_date")
	private int endDate;//失效日期

	
	@Column(name = "record_time", nullable = false)
	private Date recordTime;
	
	@Column(name = "client_id", insertable = false, updatable = false)
	private String clientId;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getImportMobile() {
		return importMobile;
	}

	public void setImportMobile(String importMobile) {
		this.importMobile = importMobile;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getIntegralNumber() {
		return integralNumber;
	}

	public void setIntegralNumber(int integralNumber) {
		this.integralNumber = integralNumber;
	}

	
	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}

	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
	}

	public String getImportItem() {
		return importItem;
	}

	public void setImportItem(String importItem) {
		this.importItem = importItem;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "CreditRecordEntity [id=" + id + ", type=" + type + ", reason=" + reason + ", reasonCode=" + reasonCode
				+ ", importItem=" + importItem + ", importMobile=" + importMobile + ", item=" + item + ", itemCode="
				+ itemCode + ", integralNumber=" + integralNumber + ", value=" + value + ", dateFlag=" + dateFlag
				+ ", groupTime=" + groupTime + ", beginDate=" + beginDate + ", endDate=" + endDate + ", recordTime="
				+ recordTime + ", clientId=" + clientId + ", mallUserEntity=" + mallUserEntity + "]";
	}

}
