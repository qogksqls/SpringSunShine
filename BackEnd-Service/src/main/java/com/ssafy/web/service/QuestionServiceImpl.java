package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ssafy.web.db.entity.questions.Question;
import com.ssafy.web.db.repository.OptionsRepository;
import com.ssafy.web.db.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	OptionsRepository optionsRepo;

	// 질문 얻어오기 
	@Override
	public List<Question> getQuestions() {
		//레디스에 저장되어있지 않다면, db에서 불러와서 레디스에 저장 
		//if문
		List<Question> queList = questionRepo.findAll();
		
		
		//레디스에 있는 값 반환
		
		
		
		return queList;
	}
	
	
}
