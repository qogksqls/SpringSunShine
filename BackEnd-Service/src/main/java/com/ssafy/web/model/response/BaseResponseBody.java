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
public class BaseResponseBody {
	@ApiModelProperty(name="응답 메시지", example="정상")
	String message =null;
	@ApiModelProperty(name="응답 코드", example="200")
	Integer statusCode= null;
	
	public BaseResponseBody() {}
	
	public BaseResponseBody(Integer statusCode) {
		this.statusCode=statusCode;
	}
	
	public BaseResponseBody(Integer statusCode, String message) {
		this.statusCode= statusCode;
		this.message=message;
	}
	
	public static BaseResponseBody of(Integer statusCode, String message) {
		BaseResponseBody body = new BaseResponseBody();
		body.statusCode = statusCode;
		body.message = message;
		return body;
	}
}
