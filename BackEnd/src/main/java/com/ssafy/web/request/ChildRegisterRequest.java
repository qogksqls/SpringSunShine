package com.ssafy.web.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/** front 에서 넘어오는 아이 정보 */
@Getter
@Setter
@ApiModel("ChildRegisterRequest")
public class ChildRegisterRequest {
	
	@ApiModelProperty(name = "부모 아이디")
	String parent_id;
	
	@ApiModelProperty(name = "아동 이름")
	String name;
	
	@ApiModelProperty(name = "아동 생년월일")
	Date birth;
	
	@ApiModelProperty(name = "아동 성별")
	int gender;
	
	@ApiModelProperty(name = "아동 프로필 사진")
	String profile_url;
	
	@ApiModelProperty(name = "문진표 응답 여부")
	int survey_flag;
}
