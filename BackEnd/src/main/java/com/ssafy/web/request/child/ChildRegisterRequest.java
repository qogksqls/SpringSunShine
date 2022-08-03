package com.ssafy.web.request.child;

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
	String parentId;
	@ApiModelProperty(name = "아이 이름")
	String name;
	@ApiModelProperty(name = "아이 생년월일")
	Date birth;
	@ApiModelProperty(name = "아이 성별")
	int gender;
	@ApiModelProperty(name = "프로필 사진")
	String profileUrl;
}
