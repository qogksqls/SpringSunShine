package com.ssafy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	 @Bean
//	 public PasswordEncoder passwordEncoder() {
//		 //BCrypt 라는 해시 함수로 비밀번호를 암호화하는 구현체
//		 return new BCryptPasswordEncoder();
//	 }
	 
//	 @Bean
//	 DaoAuthenticationProvider authenticationProvider() {
//		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		 
//		 return daoAuthenticationProvider;
//	 }
	 @Override
	    protected void configure(HttpSecurity http) throws Exception{
		 http.httpBasic().disable()
		 .csrf().disable()
		 .authorizeRequests()
		 .antMatchers("/**").authenticated()
		 .anyRequest().permitAll().and().cors();
	    }
	 
//	 @Override
//	 protected void configure(AuthenticationManagerBuilder auth) {
//		 auth.authenticationProvider(authenticationProvider());
//	 }
}
