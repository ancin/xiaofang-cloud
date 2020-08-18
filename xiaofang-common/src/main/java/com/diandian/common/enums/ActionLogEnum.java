package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2020-04-20 12:37
 * @last modify by [shengxiaohua 2020-04-20 12:37]
 **/
public enum ActionLogEnum {
    ACTION_VISIT(1,"浏览"),
    ACTION_BUY(2,"购买"),
    ACTION_SHARE(3,"分享");

    private int value;
    private String desc;

    ActionLogEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        ActionLogEnum[] lists = ActionLogEnum.values();
        for(ActionLogEnum actionLogEnum : lists){
            if(actionLogEnum.getValue()==value){
                result = actionLogEnum.getDesc();
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
