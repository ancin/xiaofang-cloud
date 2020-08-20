package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 登录类型枚举
 * @create 2020-01-06 17:47
 * @last modify by [shengxiaohua 2020-01-06 17:47]
 **/
public enum LoginTypeEnum {
    LOGIN_TYPE_USER_PWD(1,"用户名密码登录","PasswordRealm"),

    LOGIN_TYPE_OPENID(2,"openId登录","WechatRealm");

    private int value;
    private String desc;
    private String realmName;

    LoginTypeEnum(int value,String desc,String realmName){
        this.value = value;
        this.desc = desc;
        this.realmName = realmName;
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

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }
}
