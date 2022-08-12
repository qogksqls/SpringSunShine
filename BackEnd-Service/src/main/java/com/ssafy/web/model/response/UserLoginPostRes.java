package com.ssafy.web.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 서버에 대한 응답 
 * 메세지 , 응답 코드*/
@Getter
@Setter
public class UserLoginPostRes {

	String message =null;
	String userid= null;
	String accessToken; 
	String refreshToken; 

	public UserLoginPostRes() {}
	
	public UserLoginPostRes(String message, String userid, String accessToken ,String refreshToken) {
		this.message = message;
		this.userid = userid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	public static UserLoginPostRes offail(String userid, String message) {
		UserLoginPostRes res= new UserLoginPostRes() ;
		res.setUserid(userid);
		res.setMessage(message);
		return res;
	}
//	public static UserLoginPostRes ofsuccess(String userid, String message, String accessToken, String refreshToken) {
//		UserLoginPostRes res= new UserLoginPostRes() ;
//		res.setUserid(userid);
//		res.setAccessToken(accessToken);
//		res.setMessage(message);
//		res.setRefreshToken(refreshToken);
//		return res;
//	}
	
	// 재발급 할 때 줄 정보 
	public static UserLoginPostRes ofrefresh(String accessToken, String refreshToken) {
		UserLoginPostRes res= new UserLoginPostRes();
		res.setAccessToken(accessToken);
		res.setRefreshToken(refreshToken);
		return res; 
	}

}
