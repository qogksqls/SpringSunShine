package com.ssafy.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 상담사 회원가입할 떄 요청 보내는 데이터 
 * "/user/therapist" post 방식에서 사용 */
@Getter
@Setter
@ApiModel("TheraRegisterRequest")
public class TheraRegisterRequest {
	@ApiModelProperty(name="아이디")
	String id ;
	@ApiModelProperty(name="비밀번호")
	String password;
	@ApiModelProperty(name="이름")
	String name;
	@ApiModelProperty(name="이메일")
	String email;
	@ApiModelProperty(name="휴대폰")
	String phone;
	@ApiModelProperty(name="주소")
	String address;
	@ApiModelProperty(name="프로필url")
	String profile_url;
	@ApiModelProperty(name="첨부파일url")
	String file_url;

	
}
