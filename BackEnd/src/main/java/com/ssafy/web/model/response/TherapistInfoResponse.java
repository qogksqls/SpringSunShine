package com.ssafy.web.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TherapistInfoResponse {
		String id;
		String name;
		String email;
		String phone;
		String address;
		String profileUrl;
		String academicCareers;
		String careers;
		String licences;
		String theraIntro;
}
