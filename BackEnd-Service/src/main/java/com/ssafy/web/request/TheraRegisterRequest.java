package com.ssafy.web.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 상담사 회원가입할 떄 요청 보내는 데이터 
 * "/user/therapist" post 방식에서 사용 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheraRegisterRequest {
	String id ;
	String password;
	String name;
	String email;
	String phone;
	String address;
	String profile_url;
	List<Academy> academicCareers;
	List<Career> careers;
	List<Licence> licences;
	String thera_intro;
	
	
}
	
	
