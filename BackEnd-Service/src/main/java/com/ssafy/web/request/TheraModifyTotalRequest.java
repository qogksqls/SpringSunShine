package com.ssafy.web.request;

import lombok.Data;

@Data
public class TheraModifyTotalRequest {
	private String password;
	private String name;
	private String phone;
	private String address;
	private String profile_url;
	private String thera_intro;
	private int[] expertise;
	
	public TheraModifyRequest makeTMR() {
		TheraModifyRequest tmr = TheraModifyRequest.builder()
				.address(address)
				.phone(phone)
				.name(name).password(password).profile_url(profile_url)
				.thera_intro(thera_intro).build();
		return tmr;
	}
}
