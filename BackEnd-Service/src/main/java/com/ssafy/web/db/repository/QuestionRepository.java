package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.questions.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findAll();
}
