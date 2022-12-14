package com.ssafy.web.model.response;

import java.util.Date;

import lombok.Data;

@Data
public class ChildReservResponse {
	String childId;

	String name;

	Date birth;

	String gender;

//	String profileUrl;
	byte[] profileUrl;

	int surveyFlag;

	String parentName;
	
	String parentId;
	
	String parentPhone;
}
