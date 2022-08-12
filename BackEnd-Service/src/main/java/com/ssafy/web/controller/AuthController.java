package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.UserLoginPostRes;
import com.ssafy.web.request.UserLoginRequest;
import com.ssafy.web.request.UserLogoutRequest;
import com.ssafy.web.service.AuthService;

@RestController
@RequestMapping("/auth")
/**SERVICE SERVER*/
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	/**로그인*/
	@PostMapping("/login")
	public UserLoginPostRes userLogin(@RequestBody UserLoginRequest loginInfo){
		return authService.userLogin(loginInfo);
	}
	
	/**로그아웃*/
	@PostMapping("/logout")
	public ResponseEntity<?> userLogout(@RequestBody UserLogoutRequest logoutInfo){
		return authService.userLogout(logoutInfo);
	}

}
