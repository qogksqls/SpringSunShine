package com.ssafy.web.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import org.springframework.data.repository.query.Param;

import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.model.response.RecommendTherapistResponse;

public interface TheraRepository extends JpaRepository<Therapist, Integer> {

	Optional<Therapist> findByEmail(String email);
	
	@Nullable
	Therapist findByUser(User user);
	
//    @Query(value = "select "
//            + "new com.ssafy.web.model.response.RecommendTherapistResponse"
//            + "(t.user.userId as thera_id, t.name as name, t.email as email, t.profileUrl as profile_url, t.theraIntro as thera_intro) "
//            + "from Therapist t "
//            + "where t.user.userId in :thera_id")
//    List<RecommendTherapistResponse> findByUser_UserIdIn(@Param(value = "thera_id") String[] thera_id);

//    @Query(value = "select "
//    		+ "new com.ssafy.web.model.response.RecommendTherapistResponse"
//    		+ "(t.user.userId as thera_id, t.name as name, t.email as email, t.profileUrl as profile_url, t.theraIntro as thera_intro) "
//    		+ "from Therapist t ")
//    List<RecommendTherapistResponse> findAlljpql();

    List<Therapist> findByUser_UserIdIn(String[] thera_id);
    List<Therapist> findAll();
    
}
