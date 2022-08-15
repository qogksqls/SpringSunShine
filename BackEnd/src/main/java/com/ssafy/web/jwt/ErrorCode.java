package com.ssafy.web.jwt;

public enum ErrorCode {

	EXPIRED_TOKEN("1001", "토큰 유효기간 만료. 재발급 요청하세요"),
	INVALID_TOKEN("1002", "옳지 않거나 틀린형식의 토큰입니다.");
	
	private String errorCode;
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	
	ErrorCode(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
