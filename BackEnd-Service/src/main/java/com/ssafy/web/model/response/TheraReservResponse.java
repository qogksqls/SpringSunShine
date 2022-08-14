package com.ssafy.web.model.response;

import java.util.Date;

import lombok.Data;

/** 상담사가 예약 조회할 때 주는 정보 */
@Data
public class TheraReservResponse {

	private String childName;

	private String childId;

	private String parentName;

	private Date reservTime;

}
