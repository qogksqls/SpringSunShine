package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.web.db.entity.play.ObjectCard;

public interface ObjectCardRepository extends JpaRepository<ObjectCard, Integer> {

	ObjectCard findByCardId(int cardId);
	
	List<ObjectCard> findAll();
	
	@Query(value = "select count(*) from object_card", nativeQuery = true)
	int countAll();
	
}
