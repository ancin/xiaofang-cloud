package com.diandian.common.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2019-11-24 14:05
 * @last modify by [shengxiaohua 2019-11-24 14:05]
 **/
@Data
public class WxModelMsg {
    /**
     * 跳转的url
     */
    private String toUrl;
    /**
     * 微信消息模板id
     */
    private String templateId;

    private String openId;

    private String orderTitle;

    private String orderName;

    private String orderMoney;

    private String orderTime;

    private String orderRemark;
}
