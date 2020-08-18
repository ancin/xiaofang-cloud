package com.diandian.common.dto;

import com.diandian.common.enums.LoginTypeEnum;
import org.apache.shiro.authc.AuthenticationToken;

/***
 * 2019-12-05
 */
public class JwtToken extends AbstractCommonToken implements  AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public LoginTypeEnum getLoginType() {
        return LoginTypeEnum.LOGIN_TYPE_USER_PWD;
    }
}
