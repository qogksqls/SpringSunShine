package com.ssafy.web.model.response;

import java.util.List;

import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;

import lombok.Getter;
import lombok.Setter;

/**치료사의 학력, 경력, 자격 정보를 담아서 주는 객체*/
@Setter
@Getter
public class TherapistCareer {
	
	//치료사의 학력 정보 ( 대학:name, 전공:major, 입학:admin, 졸업:gradu)
	private List<Academy> academys;
	
	//치료사의 경력 정보 ( 회사:name, 직급:level, 근무기간:date, 분야:role)
	private List<Career> careers;
	
	//치료사의 자격증 정보 ( 자격증:name, 취득기관:place, 취득날짜:date, 첨부파일:file)
	private List<Licence> licences;
}
