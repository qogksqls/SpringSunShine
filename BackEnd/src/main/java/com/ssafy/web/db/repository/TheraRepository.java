package com.ssafy.web.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.model.response.RecommendTherapistResponse;

public interface TheraRepository extends JpaRepository<Therapist, Integer> {

	Optional<Therapist> findByEmail(String email);
	
	Therapist findByUser(User user);
	
//	@Query(value =" select t.thera_id thera_id, t.name name, t.email email, t.profile_url profile_url, t.thera_intro thera_intro from therapist t"
//			+ "+where thera_id in(?1)")
//	List<RecommendTherapistResponse> findByUser_UserIdIn(String... user_id);
}
