package com.olive.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	@Autowired	
	private	UserDetailsService userServieImpl;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userServieImpl).passwordEncoder(pwdEncoder);

	}


	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user/show","/user/save").permitAll()
		.antMatchers("/user/login").permitAll() 
	
		.antMatchers("/user/employee").hasAuthority("EMPLOYEE")
		.antMatchers("/user/admin").hasAuthority("ADMIN")
		.antMatchers("/user/manager").hasAnyAuthority("MANAGER")
		.anyRequest().authenticated()

		.and()
		.formLogin()
		.loginPage("/user/login") //to show login page
		.loginProcessingUrl("/login") //on submit login =>login + Post
		.defaultSuccessUrl("/user/common",true)
        .failureUrl("/user/login?error")  //login fail url
        
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/user/login?logout") //on logout success

		.and()
		.exceptionHandling()
		.accessDeniedPage("/user/denied");
	}




}
