package com.diandian.common.enums;

/**
 * @ClassName ColumnStatusEnum
 * @author: ancin
 * @create: 2020-04-21 12:35
 * @Version 1.0
 **/
public enum ColumnStatusEnum {

    UP("up","上架"),
    DOWN("down","下架");

    private  String value;
    private String desc;

    ColumnStatusEnum(String value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(String value){
        String result = "";
        ColumnStatusEnum[] lists = ColumnStatusEnum.values();
        for(ColumnStatusEnum columnStatusEnum : lists){
            if(columnStatusEnum.getValue()==value){
                result = columnStatusEnum.getDesc();
                break;
            }
        }
        return result;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
