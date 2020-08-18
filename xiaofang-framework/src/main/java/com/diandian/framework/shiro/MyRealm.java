package com.diandian.framework.shiro;

import com.diandian.common.config.SpringTools;
import com.diandian.common.dto.JwtToken;
import com.diandian.common.enums.LoginTypeEnum;
import com.diandian.common.utils.JwtUtil;
import com.diandian.entity.toc.User;
import com.diandian.service.IRoleService;
import com.diandian.service.IUserService;
import com.diandian.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class MyRealm extends AuthorizingRealm {


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserVO userVO = (UserVO) principals.getPrimaryPrincipal();
        //String username = JwtUtil.getUsername(principals.toString());
        String mobile = userVO.getMobile();
        User user = SpringTools.getBean(IUserService.class).getUserByPhoneNumber(mobile);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置用户角色
        simpleAuthorizationInfo.setRoles(SpringTools.getBean(IRoleService.class).findRoles(user.getUserId()));
       /* simpleAuthorizationInfo.setRoles(userService.findRoles(username));
        simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(username));*/
        return simpleAuthorizationInfo;
    }

    /***
     *  后面优化把Token放缓存里面
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        Long userId = JwtUtil.getUsername(token);
        if (userId == null) {
            throw new AuthenticationException("token无效");
        }

        User userBean = SpringTools.getBean(IUserService.class).getById(userId);
        if (userBean == null) {
            throw new AuthenticationException("用户不存在!");
        }

        UserVO userVO = new UserVO();
        userVO.setId(userBean.getId());
        userVO.setUserId(userBean.getId());
        userVO.setLoginName(userBean.getLoginName());
        userVO.setNickName(userBean.getNickName());
        userVO.setRealName(userBean.getRealName());
        userVO.setMobile(userBean.getMobile());
        userVO.setHeadImg(userBean.getHeadImg());
        return new SimpleAuthenticationInfo(userVO, token, LoginTypeEnum.LOGIN_TYPE_USER_PWD.getRealmName());
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
