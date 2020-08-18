package com.diandian.web.common.controller;

import com.diandian.common.config.BaseController;
import com.diandian.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;

/**
 * @author shengxiaohua
 * @Description: web端basecontroller
 * @create 2020-02-04 19:25
 * @last modify by [shengxiaohua 2020-02-04 19:25]
 **/
public class WebBaseController extends BaseController {

    /**
     * 获取用户id
     * @return
     */
    public static Long getUserIdByShiro(){
        UserVO userVO = getUserByShiro();
        if(userVO==null){
            getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        return userVO.getId();
        //return 1L;
    }

    public static UserVO getUserByShiro(){
        Subject subject = SecurityUtils.getSubject();
        Object userObject = subject.getPrincipal();
        UserVO userVO = (UserVO) userObject;
        return userVO;
    }
}
