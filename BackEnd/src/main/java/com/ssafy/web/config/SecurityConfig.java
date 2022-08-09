package com.ssafy.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.web.jwt.JwtAuthenticationFilter;
import com.ssafy.web.jwt.SssUserDetailService;
import com.ssafy.web.service.AuthService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SssUserDetailService sssUserDetailService;
	
	@Autowired
	private AuthService authService;
	 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 @Bean
	 DaoAuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		 daoAuthenticationProvider.setUserDetailsService(this.sssUserDetailService);
		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		 return daoAuthenticationProvider;
	 }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) {
		 auth.authenticationProvider(authenticationProvider());
	 }
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception{
		 http.httpBasic().disable()
		 .csrf().disable() // 세션 사용하지 않고 jwt 토큰 사용함 
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X 
		 .and()
		 .addFilter(new JwtAuthenticationFilter(authenticationManager(), authService))
		 .authorizeRequests() // 인증절차에 대한 설정 시작 
		 .antMatchers("/server/**").authenticated() // 특정 url 에 대하여 인증이 완료되어야 api 사용 가능
		 .anyRequest().permitAll().and().cors();
	    }
	 

}
