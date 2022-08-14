package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.play.Play;

public interface PlayRepository extends JpaRepository<Play, Integer> {
	
	List<Play> findByChildId(String childId);

}
