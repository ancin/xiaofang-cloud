package com.diandian.common.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.diandian.common.utils.CheckToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class OptionsInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(OptionsInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                String token = null;
                Cookie[] cookies =  request.getCookies();
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("token")){
                            token = cookie.getValue();
                        }
                    }
                }

                if (token == null) {
                    //throw new RuntimeException("无token，请重新登录");
                    logger.info("无token，请重新登录");
                }else {
                    // 获取 token 中的 user id
                    String userId;
                    try {
                        userId = JWT.decode(token).getAudience().get(0);
                        System.out.println("get userid:"+userId);
                    } catch (JWTDecodeException j) {
                        new RuntimeException("401");
                    }
                }
            }
        }

        return true;
    }
}