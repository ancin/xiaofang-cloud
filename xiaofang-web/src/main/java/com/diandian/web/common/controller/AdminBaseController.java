package com.diandian.web.common.controller;

import com.diandian.common.config.BaseController;
import com.diandian.entity.toc.User;
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
        User userVO = getUserByShiro();
        if(userVO==null){
           getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
           return null;
        }
        return userVO.getId();
    }

    public static User getUserByShiro(){
        Subject subject = SecurityUtils.getSubject();
        Object userObject = subject.getPrincipal();
        User userVO = (User) userObject;
        return userVO;
    }
}
