package com.ssafy.web.request;

import java.util.List;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheraRegisterInfo {
	private String id ;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String profile_url;
	private List<Academy> academicCareers;
	private List<Career> careers;
	private List<Licence> licences;
	private String thera_intro;
	private List<Integer> expertise;
	
	public TheraRegisterRequest makeTheraRegisterRequest() {
		TheraRegisterRequest theraInfo = new TheraRegisterRequest(id, password,  name,  email,  phone,  address,
				 profile_url,  academicCareers,  careers,  licences,
				 thera_intro);
		return theraInfo;
	}
}
