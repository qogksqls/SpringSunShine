package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.jwt.JwtTokenUtil;
import com.ssafy.web.model.response.UserLoginPostRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepository userRepository; 
	
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	public User getUserById(String id) {
		log.debug("test: service-getuserbyId입니다");
		User user = userRepository.findUserById(id).orElse(null);
		return user;
	}

	@Override
	public ResponseEntity<UserLoginPostRes> login(String id, String password) {
		log.debug("test: service-login입니다");
			String accessToken = jwtTokenUtil.createAccessToken(id);
			String refreshToken = jwtTokenUtil.createRefreshToken(id);
			User user= userRepository.findUserById(id).orElse(null);
			
			return ResponseEntity.ok(UserLoginPostRes.ofsuccess(user.getUserId(), "success", accessToken, refreshToken));
		
	}

	// 리프레시 토큰 재발급 
	@Override
	public ResponseEntity<UserLoginPostRes> refreshToken(String id, String refreshToken) {
		User user= userRepository.findUserById(id).orElse(null);
		
		if(user == null) {
			System.out.println("유효하지 않은 id 입니다. ");
			return ResponseEntity.status(401).body(UserLoginPostRes.offail(null, "fail"));
		}
		int res= jwtTokenUtil.checkRefreshToken(id, refreshToken);
		if (res==0) {
			//유효하지 않은 토큰 -> 리프레시 토큰을 재발급 하거나 , 실패 신호 보내기 
			return ResponseEntity.status(401).body(UserLoginPostRes.offail(null, "fail"));
			
		}
		// 다시 accessToken 재발급 
		String reaccessToken = jwtTokenUtil.createAccessToken(id);
		
		return ResponseEntity.ok(UserLoginPostRes.ofrefresh(reaccessToken, refreshToken));
	}

}
