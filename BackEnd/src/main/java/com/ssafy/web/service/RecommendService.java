package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.model.response.RecommendTherapistResponse;


public interface RecommendService {
//	List<RecommendTherapistResponse> findByUser_UserIdIn(String[] thera_id);
//	List<RecommendTherapistResponse> findAlljpql();
	List<RecommendTherapistResponse> findByUser_UserIdIn(String[] thera_id);
	List<RecommendTherapistResponse> findAll();
}
