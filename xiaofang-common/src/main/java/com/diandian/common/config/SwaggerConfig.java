package com.diandian.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author shengxiaohua
 * @Description: swagger配置
 * @create 2019-11-26 15:45
 * @last modify by [shengxiaohua 2019-11-26 15:45]
 **/
@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "diandianadmin.swagger.enabled", matchIfMissing = true)
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.diandian")).paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("diandian RESTful APIs").description("点点教育的API。")
                .version("1.0").build();
    }
}
