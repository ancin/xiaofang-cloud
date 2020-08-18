package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 课程枚举
 * @create 2020-04-11 12:54
 * @last modify by [shengxiaohua 2020-04-11 12:54]
 **/
public enum CourseEnum {
    /**
     * 是否审核通过
     */
    AUDIT_PASS_NO(0,"未通过"),
    AUDIT_PASS_YES(1,"已通过"),
    AUDIT_PASS_AUTH(2,"审核中");

    private int value;
    private String desc;

    CourseEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        CourseEnum[] lists = CourseEnum.values();
        for(CourseEnum courseEnum : lists){
            if(courseEnum.getValue()==value){
                result = courseEnum.getDesc();
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
