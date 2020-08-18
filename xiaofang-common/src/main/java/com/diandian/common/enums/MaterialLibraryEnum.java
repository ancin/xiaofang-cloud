package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2020-04-19 14:17
 * @last modify by [shengxiaohua 2020-04-19 14:17]
 **/
public enum MaterialLibraryEnum {
    /**
     * 是否审核通过
     */
    AUDIT_PASS_NO(0,"未通过"),
    AUDIT_PASS_YES(1,"已通过");

    private int value;
    private String desc;

    MaterialLibraryEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        MaterialLibraryEnum[] lists = MaterialLibraryEnum.values();
        for(MaterialLibraryEnum materialLibraryEnum : lists){
            if(materialLibraryEnum.getValue()==value){
                result = materialLibraryEnum.getDesc();
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
