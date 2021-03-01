package com.bridge.record.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/home/index").setViewName("home/index");
		registry.addViewController("/login/login").setViewName("login/login");
		registry.addViewController("/baseinfo/index").setViewName("baseinfo/index");
		registry.addViewController("/plan/index").setViewName("plan/index");
		registry.addViewController("/followup/index").setViewName("followup/index");
		registry.addViewController("/summary/index").setViewName("summary/index");
		registry.addViewController("/nursing/index").setViewName("nursing/index");
		//慢病管理
		registry.addViewController("/chronicCare/index").setViewName("chronicCare/index");
		//字典管理
		registry.addViewController("/datadic/index").setViewName("datadic/index");
		//用户、角色、权限管理等
		registry.addViewController("/power/index").setViewName("power/index");
		//预约就诊报到
		registry.addViewController("/order/index").setViewName("order/index");
		//预约就诊
		registry.addViewController("/order/appoint").setViewName("order/appoint");

	}

}