package com.ssafy.web.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버에 대한 응답 
 * 메세지 , 응답 코드*/
@Getter
@Setter
@ApiModel("BaseResponseBody")
public class UserLoginPostRes {
	@ApiModelProperty(name="응답 메시지", example="정상")
	String message =null;
	@ApiModelProperty(name="사용자고유아이디", example="p/t+12")
	String userid= null;
	String accessToken; // JWT 
	String refreshToken; // 재발급을 위한 refresh 토큰 추가 

	public UserLoginPostRes() {}
	
	public UserLoginPostRes(String message) {
		this.message = message;
	}
	
	public UserLoginPostRes(String message, String accessToken ,String refreshToken) {
		this.message = message;
		this.accessToken= accessToken;
		this.refreshToken= refreshToken;
	}
	
	public UserLoginPostRes(String message, String userid, String accessToken ,String refreshToken) {
		this.message = message;
		this.userid = userid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	// 재발급 할 때 줄 정보 
	public static UserLoginPostRes ofrefresh(String message, String accessToken, String refreshToken) {
		UserLoginPostRes res= new UserLoginPostRes(message, accessToken, refreshToken);
		res.setMessage(message);
		res.setAccessToken(accessToken);
		res.setRefreshToken(refreshToken);
		return res; 
	}

}
