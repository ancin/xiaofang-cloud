package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 提现状态
 * @create 2020-03-14 20:07
 * @last modify by [shengxiaohua 2020-03-14 20:07]
 **/
public enum WithDrawStatus {
    WITH_DRAW_STATUS_WAIT(1,"待审核"),
    WITH_DRAW_STATUS_SUCCESS(2,"提现成功"),
    WITH_DRAW_STATUS_FAIL(3,"提现失败"),
    WITH_DRAW_STATUS_AUTH_SUCCESS(4,"审核成功"),
    WITH_DRAW_STATUS_AUTH_FAIL(5,"审核失败");

    private Integer value;
    private String desc;

    WithDrawStatus(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(int value){
        String result = "";
        WithDrawStatus[] lists = WithDrawStatus.values();
        for(WithDrawStatus withDrawStatus : lists){
            if(withDrawStatus.getValue()==value){
                result = withDrawStatus.getDesc();
                break;
            }
        }
        return result;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
