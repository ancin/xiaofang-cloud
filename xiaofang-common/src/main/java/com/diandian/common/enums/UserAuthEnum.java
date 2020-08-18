package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 用户认证枚举
 * @create 2020-04-11 12:54
 * @last modify by [shengxiaohua 2020-04-11 12:54]
 **/
public enum UserAuthEnum {
    /**
     * 是否审核通过
     */
    AUDIT_PASS_YES(1,"已通过"),
    AUDIT_PASS_NO(2,"未通过");

    private int value;
    private String desc;

    UserAuthEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        UserAuthEnum[] lists = UserAuthEnum.values();
        for(UserAuthEnum userAuthEnum : lists){
            if(userAuthEnum.getValue()==value){
                result = userAuthEnum.getDesc();
                break;
            }
        }
        return result;
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
