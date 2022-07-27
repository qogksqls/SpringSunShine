package com.ssafy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	 @Override
	    protected void configure(HttpSecurity http) throws Exception{
		 http.httpBasic().disable()
		 .csrf().disable()
		 .authorizeRequests()
		 .antMatchers("/server/**").authenticated()
		 .anyRequest().permitAll().and().cors();
	    }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 //BCrypt 라는 해시 함수로 비밀번호를 암호화하는 구현체
		 return new BCryptPasswordEncoder();
	 }
}
