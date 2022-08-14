package com.ssafy.web.model.response;

import java.util.List;

import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

import lombok.Data;

@Data
public class RecommendTherapistResponse {
	private String thera_id;
	private String name;
	private String email;
	private String profile_url;
	private String thera_intro;
	private List<Academy> academy;
	private List<Career> careers;
	private List<Licence> licence;
}
