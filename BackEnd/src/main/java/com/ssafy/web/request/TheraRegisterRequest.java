package com.ssafy.web.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import lombok.ToString;

/**
 * 상담사 회원가입할 떄 요청 보내는 데이터 
 * "/user/therapist" post 방식에서 사용 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("TheraRegisterRequest")
public class TheraRegisterRequest {
	@ApiModelProperty(name="아이디")
	String id ;
	@ApiModelProperty(name="비밀번호")
	String password;
	@ApiModelProperty(name="이름")
	String name;
	@ApiModelProperty(name="이메일")
	String email;
	@ApiModelProperty(name="휴대폰")
	String phone;
	@ApiModelProperty(name="주소")
	String address;
	@ApiModelProperty(name="프로필url")
	String profile_url;
	
	
	@ApiModelProperty(name="첨부파일_학력")
	List<Academy> academicCareers;
	
	@ApiModelProperty(name="첨부파일_경력")
	List<Career> careers;
	
	@ApiModelProperty(name="첨부파일_자격증")
	List<Licence> licences;
	
	
	@ApiModelProperty(name="자기소개")
	String thera_intro;
	@Override
	public String toString() {
		return "TheraRegisterRequest [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", profile_url=" + profile_url + " thera_intro=" + thera_intro + "]";
	}
//	@Getter
//	@Setter
//	@NoArgsConstructor
//	@AllArgsConstructor
//	public class Academy{
//		private String name;
//		private String major;
//		private String admin;
//		private String gradu;
//	}
//	@Getter
//	@Setter
//	@NoArgsConstructor
//	@AllArgsConstructor
//	public class Career{
//		private String name;
//		private String level;
//		private String date;
//		private String role;
//	}
//	
//	@Getter
//	@Setter
//	@NoArgsConstructor
//	@AllArgsConstructor
//	public class Licence{
//		private String name;
//		private String place;
//		private String date;
//		private String file;
//	}
}
	
	
