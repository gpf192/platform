package cn.xsdzq.platform.model.mall;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRecordDTO {
    private Long id;
    private String clientName;
    private String clientId;
    private Integer tradeDate;
    private String departmentCode;
    private String departmentName;
    private String orderNo;
    private String goodsTypeId;
    private String goodsTypeName;
    private String goodsNo;
    private String goodsName;
    private String rechargeNumber;
    private BigDecimal rechargeAmount;
    private BigDecimal consumeAmount;
    private Integer useIntegral;
    private Integer orderStatus;
    private String orderStatusName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private String rechargeStatus;
    private String rechargeStatusName;
    private Integer rechargeCode;
    private String rechargeMessage;
    private Integer adjustmentType;
    private String adjustmentTypeName;
    private String adjustmentReason;
    private String operator;
    private String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Integer tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getRechargeNumber() {
        return rechargeNumber;
    }

    public void setRechargeNumber(String rechargeNumber) {
        this.rechargeNumber = rechargeNumber;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(Integer useIntegral) {
        this.useIntegral = useIntegral;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public String getRechargeStatusName() {
        return rechargeStatusName;
    }

    public void setRechargeStatusName(String rechargeStatusName) {
        this.rechargeStatusName = rechargeStatusName;
    }

    public Integer getRechargeCode() {
        return rechargeCode;
    }

    public void setRechargeCode(Integer rechargeCode) {
        this.rechargeCode = rechargeCode;
    }

    public String getRechargeMessage() {
        return rechargeMessage;
    }

    public void setRechargeMessage(String rechargeMessage) {
        this.rechargeMessage = rechargeMessage;
    }

    public Integer getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(Integer adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAdjustmentTypeName() {
        return adjustmentTypeName;
    }

    public void setAdjustmentTypeName(String adjustmentTypeName) {
        this.adjustmentTypeName = adjustmentTypeName;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
