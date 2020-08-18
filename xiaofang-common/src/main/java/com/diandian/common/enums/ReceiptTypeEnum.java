package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 钱包明细类型
 * @create 2020-03-11 20:45
 * @last modify by [shengxiaohua 2020-03-11 20:45]
 **/
public enum ReceiptTypeEnum {
    RECEIPT_TYPE_IN(1,"收入"),
    RECEIPT_TYPE_OUT(2,"支出");

    private Integer value;
    private String desc;

    ReceiptTypeEnum(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
