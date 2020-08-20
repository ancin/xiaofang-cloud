package com.diandian.framework.shiro;

import com.diandian.common.config.SpringTools;
import com.diandian.common.enums.LoginTypeEnum;
import com.diandian.entity.toc.User;
import com.diandian.service.IRoleService;
import com.diandian.service.IUserService;
import com.diandian.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/***
 * 用户名密码登录验证
 */
@Slf4j
public class PasswordRealm extends AuthorizingRealm {


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserVO userVO = (UserVO) principals.getPrimaryPrincipal();

        String mobile = userVO.getMobile();
        User user = SpringTools.getBean(IUserService.class).getUserByPhoneNumber(mobile);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置用户角色
        simpleAuthorizationInfo.setRoles(SpringTools.getBean(IRoleService.class).findRoles(user.getUserId()));

        return simpleAuthorizationInfo;
    }

    /***
     *  后面优化把Token放缓存里面
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        log.info("username:"+username+":"+password);
        // 效验用户名和密码是否为空
        if (StringUtils.isEmpty(username)){
            throw new NullPointerException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new NullPointerException("密码不能为空");
        }

        // 解密获得username，用于和数据库进行对比

        User userBean = SpringTools.getBean(IUserService.class).getByUsername(username);
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
        Object principal = userBean;
        // shiro 效验凭证
        Object credentials = userBean.getLoginPassword();
        // 加盐 用户密码加密时用到
        ByteSource credentialsSalt = ByteSource.Util.bytes("zh");
        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,this.getClass().getName());
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
