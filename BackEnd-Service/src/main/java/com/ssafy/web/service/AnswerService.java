package com.ssafy.web.service;

import com.ssafy.web.request.AnswerRequest;

public interface AnswerService {
	//아동의 응답 및 점수 저장 
	int registAnswer(AnswerRequest answerReq);


}
