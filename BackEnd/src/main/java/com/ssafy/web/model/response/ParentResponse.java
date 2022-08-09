package com.ssafy.web.model.response;

import lombok.Getter;
import lombok.Setter;

/**부모 정보 응답할 때 */
@Getter
@Setter
public class ParentResponse {
	//아이디
	String id;
	//이름
	String name;
	//이메일
	String email;
	//전화번호
	String phone;
	//주소
	String address;
}
