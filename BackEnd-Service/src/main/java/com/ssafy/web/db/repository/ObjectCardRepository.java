package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.play.ObjectCard;

public interface ObjectCardRepository extends JpaRepository<ObjectCard, Integer> {

	ObjectCard findByCardId(int cardId);
	
	List<ObjectCard> findAll();
}
