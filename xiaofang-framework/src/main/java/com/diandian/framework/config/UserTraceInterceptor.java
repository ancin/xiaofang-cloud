package com.diandian.framework.config;

import com.diandian.common.config.SpringTools;
import lombok.extern.slf4j.Slf4j;
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


    /***
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("用户log记录");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        return true;
    }
}
