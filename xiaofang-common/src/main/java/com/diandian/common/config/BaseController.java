package com.diandian.common.config;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    public static String getTokenUserId() {
        Cookie[] cookies =  getRequest().getCookies();
        String token = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }
        }
        if (token ==null){
            return null;
        }
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }
}
