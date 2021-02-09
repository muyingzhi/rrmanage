package com.bridge.record.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
	@Autowired
    MyLogOutSuccessHandler myLogOutSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
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
				.successHandler(myAuthenctiationSuccessHandler)
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
		return new myPasswordEncoder();
	}
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return myUserDetailsService;
		// PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// System.out.println(encoder.encode("1"));
		// UserDetails user =
		// 	 User.withUsername("caos").password("2")
		// 		// .password("$2a$10$TB6/dNv.iG.BY9GwEjrLYeMaVxckjXwDSmf9GqMhCaxublucCM9RK")
		// 		.roles("USER")
		// 		.build();
		// System.out.println(user.getPassword());
		// return new InMemoryUserDetailsManager(user);
	}
	// @Bean
    // @Override
    // protected AuthenticationManager authenticationManager() throws Exception {
	// 	MyAuthenticationManager a = new MyAuthenticationManager();
    //     return a;
    // }

	
}