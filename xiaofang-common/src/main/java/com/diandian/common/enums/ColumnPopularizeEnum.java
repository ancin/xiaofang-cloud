package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2020-04-19 14:45
 * @last modify by [shengxiaohua 2020-04-19 14:45]
 **/
public enum ColumnPopularizeEnum {
    /**
     * 是否审核通过
     */
    AUDIT_PASS_NO(0,"未通过"),
    AUDIT_PASS_YES(1,"已通过"),
    AUDIT_PASS_AUTH(2,"审核中");

    private int value;
    private String desc;

    ColumnPopularizeEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        ColumnPopularizeEnum[] lists = ColumnPopularizeEnum.values();
        for(ColumnPopularizeEnum columnPopularizeEnum : lists){
            if(columnPopularizeEnum.getValue()==value){
                result = columnPopularizeEnum.getDesc();
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
