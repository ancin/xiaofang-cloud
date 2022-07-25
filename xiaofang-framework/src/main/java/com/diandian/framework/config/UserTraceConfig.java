package com.diandian.framework.config;

import com.diandian.common.config.OptionsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName UserTraceConfig
 * @description:
 * @author: ancin
 * @create: 2020-04-29 13:37
 * @Version 1.0
 **/
@Configuration
public class UserTraceConfig  implements WebMvcConfigurer {

    /***
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserTraceInterceptor()).addPathPatterns("/**").excludePathPatterns("swagger-ui.html");
    }
}
