package cn.xsdzq.platform.constants;

public enum AdjustmentTypeEnum {
    DEFAULT(0, "默认值"),
    DEDUCTION(1, "扣除"),
    RETURN(2, "退回"),
    NULL(-1, "null"),

    ;

    private final Integer code;
    private final String desc;

    AdjustmentTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AdjustmentTypeEnum match(Integer adjustmentType) {
        for (AdjustmentTypeEnum at : AdjustmentTypeEnum.values()) {
            if (at.getCode().equals(adjustmentType)) {
                return at;
            }
        }
        return NULL;
    }
}
