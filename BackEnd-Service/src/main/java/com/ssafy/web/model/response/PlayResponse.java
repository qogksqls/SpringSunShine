package com.ssafy.web.model.response;

import java.time.LocalTime;
import java.util.Date;

import lombok.Data;

/** 보호자가 아동의 혼자놀기 기록을 조회할 때 주는 정보 */
@Data
public class PlayResponse {

	private String childName;

	private String childId;

	private Date playTime; // 놀이를 진행한 날짜와 시간

	private LocalTime totalTime; // 총 풀이시간

	private int score;

}
