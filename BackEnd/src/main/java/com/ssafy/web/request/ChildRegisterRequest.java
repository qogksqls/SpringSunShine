package com.ssafy.web.request;

import lombok.Getter;
import lombok.Setter;
/**front 에서 넘어오는 아이 정보 */
@Getter
@Setter
public class ChildRegisterRequest {

	String name;
	String birth;
	String gender;
}
