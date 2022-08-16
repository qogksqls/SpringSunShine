package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.web.db.entity.BExpertiseTherapist;
import com.ssafy.web.db.entity.Expertise;

public interface BExpertiseTherapistRepository extends JpaRepository<BExpertiseTherapist, Integer>{
	
	@Query(value="select thera_id from b_expertise_therapist "
			+ "where expertise_no = :expertise_no", nativeQuery = true)
	List<String> findByExpertiseNo(@Param("expertise_no") int expertise_no);
	
	@Transactional
	@Modifying
	@Query(value="delete from BExpertiseTherapist b "
			+ "where b.theraId = :thera_id")
	void deleteByTheraId(@Param("thera_id")String thera_id);
}
