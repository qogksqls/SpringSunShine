package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.db.entity.questions.Question;


public interface QuestionService {
	
	List<Question> getQuestions();

}
