package com.ssafy.web.model.response;

import java.util.List;

import com.ssafy.web.db.entity.Expertise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendTherapistTotalResponse {
	private String thera_id;
	private String name;
	private String email;
	private String profile_url;
	private String thera_intro;
	private List<Expertise> expertises;

	public RecommendTherapistTotalResponse(RecommendTherapistResponse rtr, List<Expertise> expertises) {
		this.thera_id=rtr.getThera_id();
		this.name=rtr.getName();
		this.email=rtr.getEmail();
		this.profile_url=rtr.getProfile_url();
		this.thera_intro=rtr.getThera_intro();
		this.expertises = expertises;
	}
}
