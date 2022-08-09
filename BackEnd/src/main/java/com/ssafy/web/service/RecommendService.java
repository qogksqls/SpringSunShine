package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.model.response.RecommendTherapistResponse;


public interface RecommendService {
	List<RecommendTherapistResponse> findByUser_UserIdIn(List<String> thera_id);
}
