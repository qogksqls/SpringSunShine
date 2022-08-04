package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.jwt.JwtTokenUtil;
import com.ssafy.web.model.response.UserLoginPostRes;
import com.ssafy.web.request.UserLoginRequest;
import com.ssafy.web.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import lombok.extern.slf4j.Slf4j;


/*
 * *인증
 * - 로그인 : accesstoken + refreshtoken 발급
 * - 로그아웃 : accesstoken 유효성 확인 후, redis 에서 refreshtoken 삭제 */

@Api(value="인증 API", tags= {"Auth"})
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;


	
	/* 일반로그인 */
	@PostMapping("/login")
	public ResponseEntity<?>  userLogin(@RequestBody @ApiParam(value="로그인 요청 정보", required=true) UserLoginRequest loginInfo){
		String id= loginInfo.getId();
		String password = loginInfo.getPassword();
		log.debug("test: 컨트롤러1입니다");

		User user = authService.getUserById(id);
		if(user==null) {
			return ResponseEntity.status(401).body(UserLoginPostRes.offail(null, "fail"));
		}
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			return authService.login(id, password);		
		}
		return ResponseEntity.status(401).body(UserLoginPostRes.offail(null, "fail"));
		
	}	


	/**토큰 재발급 */
	
	
	

	

	/* 로그아웃 */
//	@GetMapping("/logout/{id}")
//	public ResponseEntity<?>  userLogout(@PathVariable String id ){
//		// redis 에서 refresh token 값 삭제 
//		
//		
//	}
}
