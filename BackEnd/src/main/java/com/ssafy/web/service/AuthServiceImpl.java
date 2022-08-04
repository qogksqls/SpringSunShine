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

}
