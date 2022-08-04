package com.ssafy.web.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	
	User findByUserId(String userId);
	
//	@Query("select u.* from user u where u.id= :id")
	Optional<User> findUserById(String id);
	
}