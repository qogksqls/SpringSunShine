package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
