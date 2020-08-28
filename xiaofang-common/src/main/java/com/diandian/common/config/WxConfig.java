package com.diandian.common.config;

import com.alibaba.fastjson.JSONObject;
import com.diandian.common.constants.WxUrlConstant;
import com.diandian.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shengxiaohua
 * @Description: 微信接口主动调用配置类
 * @create 2019-11-23 16:42
 * @last modify by [shengxiaohua 2019-11-23 16:42]
 **/
@Component
public class WxConfig {
    public static String accessToken;

    private static Date accessTokenDate;

    private static long accessTokenInvalidTime = 7200L;

    /*@Value("${diandian.appId}")
    private String messageAppId;
    @Value("${diandian.appSecret}")
    private String messageSecret;*/

    @Autowired
    private HttpClientUtil httpClientUtil;

    /**
     * 从微信获取access_token
     * @return
     */
    public String getTokenFromWx(){
        String token = "";

        return token;
    }

}
