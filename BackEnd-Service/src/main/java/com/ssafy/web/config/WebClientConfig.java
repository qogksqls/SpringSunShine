package com.ssafy.web.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableCaching
public class WebClientConfig {
	
	@Bean
	public WebClient webClient() {
		return WebClient.create("https://i7a606.q.ssafy.io/auth-api");
	}
}
