package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.request.EmailRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	
	@Autowired
	WebClient webClient;
	
	/**이메일 인증메일 발송하기 */
	public String sendEmail(EmailRequest email) {
		String res=  webClient.post()
				.uri("/mail")
				.bodyValue(email)
				.retrieve()
				.bodyToMono(String.class).block();
		return res;
		
	}

}
