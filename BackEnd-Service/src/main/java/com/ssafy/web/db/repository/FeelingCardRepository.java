package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.web.db.entity.play.FeelingCard;

public interface FeelingCardRepository extends JpaRepository<FeelingCard, Integer> {
	
	FeelingCard findByCardId(int cardId);
	
	@Query(value = "select count(*) from object", nativeQuery = true)
	int countAll();

}
