package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.web.db.entity.questions.Options;

public interface OptionsRepository extends JpaRepository<Options, Integer> {
	
	@Query(value="select q.* , o.* from question q ,options o where q.question_no=o.question_no", nativeQuery = true)
	List<Options> findAll();

}
