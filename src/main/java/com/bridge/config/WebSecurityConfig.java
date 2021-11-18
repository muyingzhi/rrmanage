package com.bridge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
    MyLogOutSuccessHandler myLogOutSuccessHandler;
	@Autowired
	AjaxAuthenticationEntryPoint authenticationEntryPoint;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		VerifyCodeFilter filter = new VerifyCodeFilter();

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
				.antMatchers("/captcha/**","/signOut",
					"/login/**"
					,"/include/**"
					,"/order/appoint"
					,"/order/include/**"
					,"/service/appoint"
					,"/mock/**","/home/**","/baseinfo/**","/plan/**"
					,"/followup/**","/nursing/**","/summary/**").permitAll()
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
				.and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)  //AccessDeniedHandler
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