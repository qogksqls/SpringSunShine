package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.model.response.RecommendTherapistResponse;
import com.ssafy.web.model.response.RecommendTherapistTotalResponse;

public interface BExpertiseTherapistService {
	List<RecommendTherapistTotalResponse> recommendTherapistList(int expertise_no);
	List<RecommendTherapistTotalResponse> recommendTherapistAll();
	int getChildExp(String child_id);
	
	
}
