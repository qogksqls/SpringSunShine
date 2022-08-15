package com.ssafy.web.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenErrorResponse {
	//에러코드 : 1001(유효기간 지난 토큰) / 1002 (옳지 않은 토큰)
	int statusCode;
	//에러메세지
	String message;
}
