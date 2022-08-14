package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.dto.Question;
import com.ssafy.web.model.response.ChildAnswerResponse;
import com.ssafy.web.request.AnswerRequest;

public interface AnswerService {
	//아동의 응답 및 점수 저장 
	int registAnswer(AnswerRequest answerReq);

	//아동의 응답 및 질문 불러오기
	ChildAnswerResponse getAnswer(String child_id);


}
