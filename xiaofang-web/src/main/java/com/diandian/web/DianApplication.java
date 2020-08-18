package com.diandian.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.diandian"})
@EnableAsync
public class DianApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DianApplication.class, args);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize(DataSize.ofMegabytes(500));
		//该方法已降级
		//factory.setMaxRequestSize("30MB");
		/// 设置总上传数据总大小
		factory.setMaxRequestSize(DataSize.ofMegabytes(1000));
		return factory.createMultipartConfig();
	}
}
