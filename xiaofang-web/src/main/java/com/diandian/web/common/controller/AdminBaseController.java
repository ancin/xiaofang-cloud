package com.diandian.web.common.controller;

import com.diandian.common.config.BaseController;
import com.diandian.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;

public class AdminBaseController extends BaseController {

    /**
     * 获取用户id
     * @return
     */
    public static Long getUserIdByShiro(){
        UserVO userVO = getUserByShiro();
        if(userVO==null){
           getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
           return null;
        }
        return userVO.getId();
    }

    public static UserVO getUserByShiro(){
        Subject subject = SecurityUtils.getSubject();
        Object userObject = subject.getPrincipal();
        UserVO userVO = (UserVO) userObject;
        return userVO;
    }
}
