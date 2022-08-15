package com.ssafy.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.jwt.CustomAuthenticationEntryPoint;
import com.ssafy.web.jwt.JwtAuthorizationFilter;
import com.ssafy.web.jwt.SssUserDetailService;
import com.ssafy.web.service.AuthService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SssUserDetailService sssUserDetailService;
	
	@Autowired
	private UserRepository userRepo;
	 
	
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
		 .formLogin().disable()
		 .csrf().disable()
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
          .exceptionHandling()
          .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		 .and()
		 .addFilter(corsFilter())		 
		 .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepo)).authorizeRequests() // 인증절차에 대한 설정 시작 
//		 .antMatchers("/auth/**","/user/*" , "/child/**" , "/mail/**", "/info/**", "/therapist/recommed", "/therapist/recommedall" ).permitAll() // 로그인, 회원가입은 누구나 접근 가능 
		 .antMatchers("/authentication/**").authenticated()
//		 .anyRequest().authenticated();
		 .anyRequest().permitAll();
//		 .and()
//		 .exceptionHandling()
//         .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
	    }

		@Bean
		public CorsFilter corsFilter() {
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.addAllowedOriginPattern("*");
	        configuration.addAllowedMethod("*");
	        configuration.addAllowedHeader("*");
	        configuration.addExposedHeader("*");
	        configuration.setAllowCredentials(true);
	        configuration.setMaxAge(3600L);
	        source.registerCorsConfiguration("/**", configuration);
	        return new CorsFilter(source);

		}
//		
//		// 다른 곳에서 AuthenticationManager 를 사용하기 위한 bean 등록 
//		@Bean
//		public void configure(WebSecurity web) throws Exception{
//			web.ignoring()
//			.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//		}

}
