package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 支付状态枚举
 * @create 2020-02-17 16:16
 * @last modify by [shengxiaohua 2020-02-17 16:16]
 **/
public enum PayStatusEnum {
    PAY_STATUS_NO(1,"待支付"),
    PAY_STATUS_YES(2,"已支付"),
    PAY_STATUS_BACK(3,"已退款"),
    PAY_STATUS_CLOSE(4,"已关闭");

    private int value;
    private String desc;

    PayStatusEnum(int value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
