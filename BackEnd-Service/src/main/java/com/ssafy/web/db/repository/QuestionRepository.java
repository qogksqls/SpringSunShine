package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.questions.Question;


public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
//	  @Query(value="select distinct q.* , o.option_number, o.option_context, o.option_score  from question q inner join options o where q.question_no=o.question_no", nativeQuery = true) 
	  List<Question> findAll();
	
	
	 


}

