package com.ssafy.web.model.response;

import java.util.List;

import com.ssafy.web.dto.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//아동의 응답 결과를 반환해주는 response 객체 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildAnswerResponse {
	
	//아동증상
	String expertise;
	//아동의 질문 , 고른 답 문항 
	List<Question> answers;
}
