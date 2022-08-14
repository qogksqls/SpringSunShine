package com.ssafy.web.model.response;

import java.util.List;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

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
	private List<Academy> academy;
	private List<Career> careers;
	private List<Licence> licence;
	private List<Expertise> expertises;

	public RecommendTherapistTotalResponse(RecommendTherapistResponse rtr, List<Expertise> expertises) {
		this.thera_id=rtr.getThera_id();
		this.name=rtr.getName();
		this.email=rtr.getEmail();
		this.profile_url=rtr.getProfile_url();
		this.thera_intro=rtr.getThera_intro();
		this.academy = rtr.getAcademy();
		this.careers = rtr.getCareers();
		this.licence = rtr.getLicence();
		this.expertises = expertises;
	}
}
