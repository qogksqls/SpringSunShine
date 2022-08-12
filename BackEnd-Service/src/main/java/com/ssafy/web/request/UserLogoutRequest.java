package com.ssafy.web.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogoutRequest {
	String accessToken ;
	String refreshToken;
}
