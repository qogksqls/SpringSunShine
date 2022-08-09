package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.model.response.ExpertiseResponse;
import com.ssafy.web.model.response.RecommendTherapistResponse;

public interface ExpertiseService {
	List<ExpertiseResponse> expertiseFindAll();

}
