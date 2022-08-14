package com.ssafy.web.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 부모 회원가입할 떄 요청 보내는 데이터 
 * "/user/parent" post 방식에서 사용 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentRegisterRequest {
	String id;
	String password;
	String name;
	String email;
	String phone;
	String address;
}
