package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.model.response.UserLoginPostRes;
import com.ssafy.web.request.UserLoginRequest;
import com.ssafy.web.request.UserLogoutRequest;
@Service
public class AuthService {

	@Autowired
	WebClient webClient; 
	
	
	/**일반로그인*/
	public UserLoginPostRes userLogin(UserLoginRequest loginInfo) {
		return webClient.post()
				.uri("/auth/login")
				.bodyValue(loginInfo)
				.retrieve()
				.bodyToMono(UserLoginPostRes.class).block();
	}
	
	
	/**로그아웃*/
	public ResponseEntity<?> userLogout(UserLogoutRequest logoutInfo) {
		try {
			String res = webClient.post()
			.uri("/auth/logout")
			.bodyValue(logoutInfo)
			.retrieve()
			.bodyToMono(String.class).block();
			return new ResponseEntity<String> (res, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("fail" , HttpStatus.BAD_REQUEST);
		}
	}

	/**토큰 재발급 */
	public UserLoginPostRes refreshToken(String header, String id) {
		return webClient.get()
				.uri("/auth/refresh/"+id)
				.header("Authorization", header)
				.retrieve()
				.bodyToMono(UserLoginPostRes.class).block();
				
	}
	

}
