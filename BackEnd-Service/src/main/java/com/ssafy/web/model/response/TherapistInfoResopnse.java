package com.ssafy.web.model.response;

import java.util.List;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

import lombok.Data;

@Data
public class TherapistInfoResopnse {
	private String id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String profile_url;
	private String thera_intro;
	private List<Academy> academy;
	private List<Career> careers;
	private List<Licence> licence;
	private List<Expertise> expertises;
	
	public TherapistInfoResopnse(TherapistResponse tr, List<Expertise> ex){
		expertises = ex;
		id =tr.getId();
		academy=tr.getAcademy();
		address=tr.getAddress();
		careers=tr.getCareers();
		email=tr.getEmail();
		licence=tr.getLicence();
		name=tr.getName();
		phone=tr.getPhone();
		profile_url=tr.getProfile_url();
		thera_intro=tr.getThera_intro();
	}
	
	
	
}
