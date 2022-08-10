package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.questions.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	
	Answer findAnswerByChildId(String child_id); 

}

