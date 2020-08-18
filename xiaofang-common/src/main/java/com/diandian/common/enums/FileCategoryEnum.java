package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 文件种类枚举
 * @create 2019-12-22 14:19
 * @last modify by [shengxiaohua 2019-12-22 14:19]
 **/
public enum FileCategoryEnum {
    FILE_CATEGORY_COLUMN(1,"专栏"),
    FILE_TYPE_COURSE(2,"单课");

    private int value;
    private String desc;

    FileCategoryEnum(int value,String desc){
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
