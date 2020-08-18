package com.diandian.common.dto;

import lombok.Data;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2019-11-24 14:23
 * @last modify by [shengxiaohua 2019-11-24 14:23]
 **/
@Data
public class WxMsgOption {
    private String value;
    private String color;

    public WxMsgOption(){}

    public WxMsgOption(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
