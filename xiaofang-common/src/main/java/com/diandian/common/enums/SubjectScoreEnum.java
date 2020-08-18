package com.diandian.common.enums;

public enum SubjectScoreEnum {

    JAINDAN(1,"简单"),
    ZHONGDNEG(2,"中等"),
    NAN(3,"难");

    private int value;
    private String desc;

    SubjectScoreEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        SubjectScoreEnum[] lists = SubjectScoreEnum.values();
        for(SubjectScoreEnum columnPopularizeEnum : lists){
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
