package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.BExpertiseTherapist;

public interface BExpertiseTherapistRepository extends JpaRepository<BExpertiseTherapist, Integer>{
	
//	@Query("select thera_id from b_expertise_therapist")
//	List<String> findByExpertiseNo(int expertise_no);
	
}
