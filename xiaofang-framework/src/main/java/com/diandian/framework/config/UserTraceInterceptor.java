package com.diandian.framework.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.diandian.common.config.SpringTools;
import com.diandian.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserTraceInterceptor
 * @description:
 * @author: ancin
 * @create: 2020-04-29 13:36
 * @Version 1.0
 **/
@Slf4j
public class UserTraceInterceptor implements HandlerInterceptor {

    private ApplicationContext context =  SpringTools.getApplicationContext();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("用户log记录");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            Object userObject = subject.getPrincipal();
            if (userObject!=null){
                UserVO userVO = (UserVO) userObject;
                log.info("用户行为监控日志");

            }
        } catch (JWTDecodeException j) {
            log.error("拦截异常",j);
            return true;
        }
        return true;
    }
}
