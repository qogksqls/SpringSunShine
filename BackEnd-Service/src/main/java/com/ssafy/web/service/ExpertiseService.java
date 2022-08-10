package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.model.response.ExpertiseResponse;
import com.ssafy.web.model.response.RecommendTherapistResponse;

public interface ExpertiseService {
	List<ExpertiseResponse> expertiseFindAll();
	List<Expertise> findByTheraId(String thera_id);
}
