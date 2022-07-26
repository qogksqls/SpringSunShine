package com.ssafy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception{
	        httpSecurity.httpBasic().disable();
	    }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 //BCrypt 라는 해시 함수로 비밀번호를 암호화하는 구현체
		 return new BCryptPasswordEncoder();
	 }
}
