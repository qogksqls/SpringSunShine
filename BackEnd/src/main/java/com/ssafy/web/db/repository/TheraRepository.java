package com.ssafy.web.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.model.response.RecommendTherapistResponse;

public interface TheraRepository extends JpaRepository<Therapist, Integer> {

	Optional<Therapist> findByEmail(String email);
	
	Therapist findByUser(User user);
	
	@Query(value =" select thera_id, name, email, profile_url, thera_intro from therapist "
			+ "+where thera_id in(:thera_id)", nativeQuery = true)
	List<RecommendTherapistResponse> findByUser_UserIdIn(@Param(value="thera_id") List<String> thera_id);
}
