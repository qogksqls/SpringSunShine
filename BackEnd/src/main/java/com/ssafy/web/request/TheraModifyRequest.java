package com.ssafy.web.request;

import lombok.Getter;
import lombok.Setter;

/**치료사 정보 수정할 정보 request
 * */

@Getter
@Setter
public class TheraModifyRequest {	
	String password;

	String name;
	
	String phone;

	String address;

	String profile_url;

	String thera_intro;
}
