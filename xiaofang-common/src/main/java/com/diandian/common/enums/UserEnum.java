package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 用户枚举
 * @create 2020-04-11 12:38
 * @last modify by [shengxiaohua 2020-04-11 12:38]
 **/
public enum UserEnum {
    FINISH_AUTH_PASS(1,"已完成实名认证"),
    FINISH_AUTH_NO(0,"未完成实名认证");

    private int value;
    private String desc;

    UserEnum(int value,String desc){
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
