package cn.xsdzq.platform.model.mall;

public class OrderSaveDTO {
    private Long id;
    private Integer adjustmentType;
    private String adjustmentReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(Integer adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

}