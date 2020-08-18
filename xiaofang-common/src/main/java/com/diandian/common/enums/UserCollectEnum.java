package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 用户收藏表枚举
 * @create 2020-04-05 20:39
 * @last modify by [shengxiaohua 2020-04-05 20:39]
 **/
public enum UserCollectEnum {
    TYPE_COLUMN(1,"专栏"),
    TYPE_COURSE(2,"课程"),
    TYPE_ROOM(3,"直播间");

    private int value;
    private String desc;

    UserCollectEnum(int value,String desc){
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
