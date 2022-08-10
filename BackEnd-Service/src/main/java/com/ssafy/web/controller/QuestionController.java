package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.questions.Question;
import com.ssafy.web.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService; 
	
	//문진표 질문 리스트
	@GetMapping
	public ResponseEntity<?> getQuests(){
		List<Question> quelists= questionService.getQuestions();
		return new ResponseEntity<List<Question>>(quelists, HttpStatus.OK);
	}
}
