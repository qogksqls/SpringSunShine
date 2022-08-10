package com.ssafy.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.RecommendTherapistResponse;
import com.ssafy.web.service.BExpertiseTherapistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/therapist/recommed")
public class BExpertiseThearpistController {
	private final BExpertiseTherapistService BETService;
	
	@GetMapping("/{expertise_no}")
	public RecommendTherapistResponse[] recommed(@PathVariable int expertise_no){
		RecommendTherapistResponse[] recommedTheraArr = BETService.recommendTherapistList(expertise_no);
		return recommedTheraArr;
	}
	
	@GetMapping("/all")
	public RecommendTherapistResponse[] recommedAll(){
		RecommendTherapistResponse[] recommedTheraArr = BETService.recommendTherapistAll();
		return recommedTheraArr;
	}
}
