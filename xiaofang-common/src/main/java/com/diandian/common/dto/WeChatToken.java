package com.diandian.common.dto;

import com.diandian.common.enums.LoginTypeEnum;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author shengxiaohua
 * @Description: 微信认证token
 * @create 2020-01-06 15:49
 * @last modify by [shengxiaohua 2020-01-06 15:49]
 **/
public class WeChatToken extends AbstractCommonToken implements AuthenticationToken {

    private String openId;

    public WeChatToken(String openId){
        this.openId = openId;
    }

    @Override
    public Object getPrincipal() {
        return openId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public LoginTypeEnum getLoginType() {
        return LoginTypeEnum.LOGIN_TYPE_OPENID;
    }
}
