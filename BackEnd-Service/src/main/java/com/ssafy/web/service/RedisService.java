package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.questions.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
	private final RedisTemplate<String, String> redisTemplate;
	String key = "questions";
	//문단표 질문 레디스에 저장 
	public void setQuestions(List<Question> list) {
	
		ListOperations<String, String> stringOperations = redisTemplate.opsForList();
		
		for(int i=0; i<list.size(); i++) {
			String ques = list.get(i).getQuestionContext();
			stringOperations.rightPush(key, ques);
		}
		
	}
	
	//질문 불러오기
	public List<String> getQuestions(){
		ListOperations<String, String> stringOperations = redisTemplate.opsForList();
		Long size= stringOperations.size(key);
		List<String> ques = stringOperations.range(key, 0, size-1); 
		return ques;
	}
	
	
}
