package com.ssafy.web.request;

import java.util.List;

import com.ssafy.web.dto.Answerlist;

/**프론트에서 넘어온 문진표에 대한 정보 */
public class AnswerRequest {

	//아동이름 
	String child_name;
	
	//응답 (질문번호 : 답 ) 리스트 
	List<Answerlist> answer;
	
}
