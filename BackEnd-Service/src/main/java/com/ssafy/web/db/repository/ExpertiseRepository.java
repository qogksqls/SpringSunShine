package com.ssafy.web.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.web.db.entity.Expertise;

public interface ExpertiseRepository extends JpaRepository<Expertise, Integer>{
	
	@Query(value="select "
			+ "e.expertise_no as expertise_no, e.is_kind as is_kind "
			+ "from expertise e join b_expertise_therapist b "
			+ "where e.expertise_no =b.expertise_no "
			+ "and b.thera_id = :thera_id ", nativeQuery = true)
	List<Expertise> findByTheraIdjpql(@Param("thera_id") String thera_id);
	
	Expertise findByExpertiseNo(int expertise_no);
}
