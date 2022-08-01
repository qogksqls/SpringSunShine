package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.login;

public interface LoginRepository extends JpaRepository<login, Integer>{

}
