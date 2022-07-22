package com.ssafy.web.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * 사용자가 일반 로그인 할때 요청 보내는 아이디와 패스워드
 * "/auth/login" 에서 사용함  */
@Getter
@Setter
@ApiModel("UserLoginRequest")
public class UserLoginRequest {
	@ApiModelProperty(name="아이디")
	String id;
	@ApiModelProperty(name="비밀번호")
	String password;
}
