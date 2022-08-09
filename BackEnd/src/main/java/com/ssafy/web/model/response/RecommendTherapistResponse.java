package com.ssafy.web.model.response;

import com.ssafy.web.db.entity.Therapist;

import lombok.Data;

@Data
public class RecommendTherapistResponse {
		String thera_id;
		String name;
		String email;
		String profile_url;
		String thera_intro;
		
		
		
}
