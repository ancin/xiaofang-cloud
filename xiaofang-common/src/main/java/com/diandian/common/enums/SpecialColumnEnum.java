package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 专栏枚举
 * @create 2020-04-11 12:54
 * @last modify by [shengxiaohua 2020-04-11 12:54]
 **/
public enum SpecialColumnEnum {
    /**
     * 是否审核通过
     */
    AUDIT_PASS_NO(0,"未通过"),
    AUDIT_PASS_YES(1,"已通过"),
    AUDIT_PASS_AUTH(2,"审核中"),

    COLUMN_TYPE_PAY(1,"付费专栏"),
    COLUMN_TYPE_FREE(2,"免费专栏");

    private int value;
    private String desc;

    SpecialColumnEnum(int value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        SpecialColumnEnum[] lists = SpecialColumnEnum.values();
        for(SpecialColumnEnum specialColumnEnum : lists){
            if(specialColumnEnum.getValue()==value){
                result = specialColumnEnum.getDesc();
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
