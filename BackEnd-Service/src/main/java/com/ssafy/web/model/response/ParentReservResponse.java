package com.ssafy.web.model.response;

import java.util.Date;

import lombok.Data;

/** 보호자가 예약 조회할 때 주는 정보 */
@Data
public class ParentReservResponse {
	
	private String childName;
	
	private String childId;
	
	private String theraName;
	
	private String theraId;

	private Date reservTime;
}
