package com.diandian.framework.shiro;

import com.diandian.common.config.SpringTools;
import com.diandian.common.dto.WeChatToken;
import com.diandian.common.enums.LoginTypeEnum;
import com.diandian.entity.toc.User;
import com.diandian.service.IUserService;
import com.diandian.vo.UserVO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

/**
 * @author shengxiaohua
 * @Description: 自定义微信Realm
 * @create 2020-01-06 15:59
 * @last modify by [shengxiaohua 2020-01-06 15:59]
 **/
public class WechatRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        return simpleAuthorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        WeChatToken token = (WeChatToken) authcToken;
        String openId = (String) token.getPrincipal();
        if (openId == null) {
            throw new AuthenticationException("openId无效");
        }
        User user = SpringTools.getBean(IUserService.class).getUserByOpenId(openId);
        if (user == null) {
            throw new AuthenticationException("openId未绑定用户!");
        }
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUserId(user.getId());
        userVO.setNickName(user.getNickName());
        userVO.setHeadImg(user.getHeadImg());
        userVO.setMobile(user.getMobile());
        userVO.setRealName(user.getRealName());
        userVO.setFinishAuth(user.getFinishAuth());
        userVO.setHeadImg(user.getHeadImg());
        return new SimpleAuthenticationInfo(userVO, token, LoginTypeEnum.LOGIN_TYPE_OPENID.getRealmName());
    }

    @Override
    public boolean supports(AuthenticationToken token){
        return token!=null && token instanceof WeChatToken;
    }

    /**
     * 认证密码匹配调用方法
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) throws AuthenticationException {
        return;
    }
}
