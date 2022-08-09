package com.ssafy.web.service;

import org.springframework.http.ResponseEntity;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.model.response.UserLoginPostRes;

public interface AuthService {

	User getUserById(String id);

	public ResponseEntity<UserLoginPostRes> login(String id, String password);

	public ResponseEntity<UserLoginPostRes> refreshToken(String id, String refreshToken);

}
