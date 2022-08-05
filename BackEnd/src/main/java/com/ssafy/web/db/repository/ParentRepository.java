package com.ssafy.web.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.User;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

	Parent findByUser(User user);
//	Parent findByUser(String user_id);
	
	Optional<Parent> findByEmail(String email);

}
