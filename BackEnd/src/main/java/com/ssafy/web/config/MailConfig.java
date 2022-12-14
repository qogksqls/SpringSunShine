package com.ssafy.web.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**이메일 인증을 위한 설정 파일 */
@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.naver.com");
		javaMailSender.setUsername("sss_a606@naver.com");
		javaMailSender.setPassword("rhdxhdvmfhwprxm"); // (공통프로젝트 영어로)
		
		javaMailSender.setPort(465);
		
		javaMailSender.setJavaMailProperties(getMailProperties());
		javaMailSender.setDefaultEncoding("UTF-8");
		
		return javaMailSender;
	}
	
	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
	    properties.setProperty("mail.debug", "true");
	    properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com");
	    properties.setProperty("mail.smtp.ssl.enable","true");
	    return properties;
		
	}
}
