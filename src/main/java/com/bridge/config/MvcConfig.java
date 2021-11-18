package com.bridge.config;

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
		registry.addViewController("/order/index").setViewName("order/index");
		registry.addViewController("/datadic/index").setViewName("datadic/index");
		registry.addViewController("/power/index").setViewName("power/index");


		registry.addViewController("/order/appoint").setViewName("order/appoint");

	}

}