package com.diandian.common.enums;

public enum SubjectTypeEnum {

    CHANGSHIPANDUAN(1,"常识判断"),
    YUYANLIJIEYUBIAODA(2,"言语理解与表达"),
    SHULIANGGUANXI(3,"数量关系"),
    PANDUANTUILI(4,"判断推理"),
    ZILIAOFENXI(5,"资料分析");

    private int value;
    private String desc;

    SubjectTypeEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        SubjectTypeEnum[] lists = SubjectTypeEnum.values();
        for(SubjectTypeEnum columnPopularizeEnum : lists){
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
