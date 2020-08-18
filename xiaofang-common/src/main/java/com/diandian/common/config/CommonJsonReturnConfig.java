package com.diandian.common.config;

import com.diandian.common.dto.ResultBase;
import com.diandian.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author shengxiaohua
 * @Description:  统一异常返回、和统一返回json格式
 * @create 2019-11-24 14:58
 * @last modify by [shengxiaohua 2019-11-24 14:58]
 **/
@Configuration
@Slf4j
public class CommonJsonReturnConfig {
    @RestControllerAdvice("com.diandian.web")
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            if (body instanceof ResultBase) {
                return body;
            }
            if(body instanceof  String){
                return new ResultBase<Object>(body);
            }
            return new ResultBase<Object>(body);
        }

        @ResponseBody
        @ExceptionHandler(value = BusinessException.class)
        public ResultBase myErrorHandler(BusinessException ex) {
            String errorMsg = "startRRException("+ex.getMsg()+")";
            log.error(errorMsg,ex);
            return new ResultBase<>(ex.getMsg(), ex.getCode()+"");
        }

        @ResponseBody
        @ExceptionHandler(Exception.class)
        public ResultBase apiExceptionHandler(Exception ex) {
            log.error("startException：",ex);
            return new ResultBase<>(ex.getMessage(), "");
        }
    }
}
