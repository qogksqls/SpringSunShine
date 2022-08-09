package com.ssafy.web.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ssafy.web.model.response.RecommendTherapistMapping;
import com.ssafy.web.model.response.RecommendTherapistResponse;


public interface RecommendService {
	List<RecommendTherapistResponse> findByUser_UserIdIn(String... user_id);
}
