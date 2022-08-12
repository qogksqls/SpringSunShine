package com.ssafy.web.model.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildResponse {
	String childId;
	
	String name;

	Date birth;

	String gender;

	String profileUrl;
	
	int surveyFlag;

}
