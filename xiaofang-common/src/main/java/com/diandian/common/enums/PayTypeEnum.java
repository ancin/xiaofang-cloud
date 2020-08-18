package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 支付类型枚举
 * @create 2020-02-19 19:37
 * @last modify by [shengxiaohua 2020-02-19 19:37]
 **/
public enum PayTypeEnum {
    PAY_TYPE_WX(1,"微信"),
    PAY_TYPE_ZHIFUBAO(2,"支付宝"),
    PAY_TYPE_YINLIAN(3,"银联");

    private Integer value;

    private String desc;

    PayTypeEnum(int value,String desc){
        this.value = value;
        this.desc = desc;
    }
}
