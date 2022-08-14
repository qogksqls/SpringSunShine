package com.ssafy.web.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.questions.Question;
import com.ssafy.web.db.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
	@Autowired
	QuestionRepository ques;
	private final RedisTemplate<String, String> redisTemplate;
	String key = "questions";
	//문단표 질문 레디스에 저장 
	public void setQuestions() {
		List<Question> list = ques.findAll();
		
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
	
	public void setValues(String key, String data) {
		ValueOperations<String, String> values= redisTemplate.opsForValue();
		values.set(key,data);
	}
	
	public void setValues(String key, String data, Duration duration) {
		ValueOperations<String, String> values= redisTemplate.opsForValue();
		values.set(key,data,duration);
	}
	
	public String getValues(String key) {
		ValueOperations<String, String> values= redisTemplate.opsForValue();
		return values.get(key);
	}
	
	public void deleteValues(String key) {
		redisTemplate.delete(key);
	}
	
	
}
