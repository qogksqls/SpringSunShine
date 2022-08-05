package com.ssafy.web.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ssafy.web.db.entity.Therapist;

public interface TheraRepository extends JpaRepository<Therapist, Integer> {

	Optional<Therapist> findByEmail(String email);
}
