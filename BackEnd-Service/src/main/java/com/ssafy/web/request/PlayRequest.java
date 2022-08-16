package com.ssafy.web.request;

import java.time.LocalTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 놀이 진행 후 front에서 넘어오는 정보 */
@Data
@ApiModel("PlayRequest")
public class PlayRequest {

	@ApiModelProperty(name = "아동 아이디")
	String childId;
	
	@ApiModelProperty(name = "생성 시간")
	Date createTime;
	
	@ApiModelProperty(name = "풀이 시간")
	LocalTime totalTime;
	
	@ApiModelProperty(name = "점수")
	int score;
}
