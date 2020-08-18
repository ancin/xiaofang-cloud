package com.diandian.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URI;

/**
 * @author shengxiaohua
 * @Description: 支付时的同步返回参数
 * @create 2020-02-13 14:59
 * @last modify by [shengxiaohua 2020-02-13 14:59]
 **/
@Data
public class PayResponse {

    private String prePayParams;

    private URI payUri;

    /** 以下字段仅在微信h5支付返回. */
    private String appId;

    private String timeStamp;

    private String nonceStr;

    @JsonProperty("package")
    private String packAge;

    private String signType;

    private String paySign;

    /** 以下字段在微信异步通知下返回. */
    private Double orderAmount;

    /**
     * 商户订单号
     */
    private String orderId;
    //第三方支付的流水号
    private String outTradeNo;
}