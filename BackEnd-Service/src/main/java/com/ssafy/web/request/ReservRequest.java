package com.ssafy.web.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/** 보호자가 상담 예약 시 들어오는 정보 */
@Getter
@Setter
@ApiModel("ReservRegisterRequest")
public class ReservRequest {

	@ApiModelProperty(name = "부모 아이디")
	String parent_id;

	@ApiModelProperty(name = "아동 이름")
	String child_name;

	@ApiModelProperty(name = "상담사 아이디")
	String thera_id;

	@ApiModelProperty(name = "예약시간")
	Date reserv_time;
	
	
}
