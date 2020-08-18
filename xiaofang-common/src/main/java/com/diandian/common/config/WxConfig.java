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

    @Value("${diandian.appId}")
    private String messageAppId;
    @Value("${diandian.appSecret}")
    private String messageSecret;

    @Autowired
    private HttpClientUtil httpClientUtil;

    /**
     * 从微信获取access_token
     * @return
     */
    public String getTokenFromWx(){
        String token = "";
        //判断accessToken不存在就直接申请，判断是否过期，否则不用请求直接返回的token
        if(StringUtils.isEmpty(accessToken) || accessTokenDate==null || (new Date().getTime() - accessTokenDate.getTime())>=((accessTokenInvalidTime-200L)*1000L)){
            String accessTokenUrl = WxUrlConstant.URL_GET_TOKEN+"grant_type=client_credential&&appid="+messageAppId+"&secret="+messageSecret;
            JSONObject resultObj = httpClientUtil.createGetMsg(accessTokenUrl);
            if(resultObj!=null && null!=resultObj.get("access_token")){
                token = resultObj.get("access_token").toString();
                //更新有效时间
                accessTokenInvalidTime = Long.valueOf(resultObj.get("expires_in").toString());
                accessToken = token;
                accessTokenDate = new Date();
            }
        }else{
            token = accessToken;
        }
        return token;
    }

    public String getMessageAppId() {
        return messageAppId;
    }

    public String getMessageSecret() {
        return messageSecret;
    }
}
