package com.diandian.common.config;

import com.auth0.jwt.JWT;
import com.diandian.entity.toc.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
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

    public static Long getUserIdByShiro(){
        User userVO = getUserByShiro();
        if(userVO==null){
            getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        return userVO.getId();
        //return 1L;
    }
    public static User getUserByShiro(){
        Subject subject = SecurityUtils.getSubject();
        Object userObject = subject.getPrincipal();
        User userVO = (User) userObject;
        log.info("BaseController get User:"+userVO);
        return userVO;
    }
}
