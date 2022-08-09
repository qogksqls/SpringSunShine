package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.BExpertiseTherapist;
import com.ssafy.web.model.response.BExpertiseTherapistMapping;

public interface BExpertiseTherapistRepository extends JpaRepository<BExpertiseTherapist, Integer>{
	List<BExpertiseTherapistMapping> findByExpertiseNo(int expertise_no);
}
