package com.ssafy.web.controller;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.jwt.JwtTokenUtil;
import com.ssafy.web.model.response.UserLoginPostRes;
import com.ssafy.web.request.UserLoginRequest;
import com.ssafy.web.request.UserLogoutRequest;
import com.ssafy.web.service.AuthService;
import com.ssafy.web.service.RedisService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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
	
	@Autowired
	RedisService redis;


	
	/* 일반로그인 */
	@PostMapping("/login")
	public UserLoginPostRes  userLogin(@RequestBody @ApiParam(value="로그인 요청 정보", required=true) UserLoginRequest loginInfo){
		String id= loginInfo.getId();
		String password = loginInfo.getPassword();
		User user = authService.getUserById(id);
		if(user==null) {
//			return ResponseEntity.status(401).body(UserLoginPostRes.offail(null, "fail"));
			return new UserLoginPostRes("fail", null, null, null);
		}
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			return authService.login(id, password);		
		}
		return new UserLoginPostRes("fail", null, null, null);
		
	}	


	/**토큰 재발급 */
	// front 에서는 기존 accessToken 을 버리고 새로 발급받아준걸로 사용해야함 
	@GetMapping("/refresh/{id}")
	public UserLoginPostRes refreshToken(@PathVariable String id, 
			@RequestHeader("Authorization") String refreshToken){
		// id : 사용자 아이디 
		String refreshTokens = refreshToken.replace("Bearer ", "");
		log.debug("리프레시토큰 : " + refreshTokens);

		//리프레시 토큰을 재발급 받는다 .
		return authService.refreshToken(id, refreshTokens); 	
	}
	
	
	

	

	/* 로그아웃 */
	@PostMapping("/logout")
	public String  userLogout(@RequestBody UserLogoutRequest logoutInfo) throws Exception{
		// redis 에서 refresh token 값 삭제 
		String token = logoutInfo.getAccessToken();
		log.debug("test: 컨트롤러2입니다");
		try{
			JwtTokenUtil.handleError(token);
		}catch(ExpiredJwtException e) {
			log.debug("토큰만료");
			throw new JwtException("토큰기한이 만료되었습니다.");
		}catch(Exception e) {
			log.debug("토큰 x");
			throw new Exception("올바르지 않은 토큰 입니다.");
		}
		//토큰으로부터 얻은 사용자의 아이디
		String id = jwtTokenUtil.getAuth(token);
		
		//redis에서 해당 아이디로 저장된 value 값이 있는지 검사 
		if(redis.getValues(id) !=null) {
			//redis 토큰 삭제 
			redis.deleteValues(id);
		}
		
		
		Long expiration = jwtTokenUtil.getExpiration(token);
		redis.setValues("logout",token, Duration.ofMillis(expiration));
		return "success";
		
	}
}
