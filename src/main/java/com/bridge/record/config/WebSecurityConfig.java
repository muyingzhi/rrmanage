package com.bridge.record.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridge.common.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
    MyLogOutSuccessHandler myLogOutSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		VerifyCodeFilter filter = new VerifyCodeFilter();
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
				.antMatchers("/captcha/**","/signOut",
					"/login/**"
					,"/include/**"
					,"/mock/**","/home/**","/baseinfo/**","/plan/**"
					,"/followup/**","/summary/**").permitAll()
				.antMatchers("/api/**")
				.hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.headers().frameOptions().disable()
				.and()
			.formLogin()
				.loginPage("/login/login")
				.loginProcessingUrl("/rrlogin")
				.successHandler(new MyAuthenticationSuccessHandler())
				.failureHandler(new MyAuthenticationFailureHandler())
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/signOut")
				.permitAll()
				.and()
			
			.csrf().disable()
		;
	}
	//认证用户的来源
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
		.passwordEncoder(passwordEncoder());
		// auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("caos").password(new BCryptPasswordEncoder().encode("1")).roles("USER");

    }

	@Bean
	// @Override
	public PasswordEncoder passwordEncoder() {
		return new RawPasswordEncoder();
	}
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return myUserDetailsService;
	}

	
}