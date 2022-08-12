package com.ssafy.web.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.model.response.RecommendTherapistResponse;
import com.ssafy.web.service.BExpertiseTherapistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/therapist/recommend")
public class BExpertiseThearpistController {
	private final BExpertiseTherapistService BETService;
	
	@Autowired
	WebClient webClient; 
	
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
	
//	//아동관리페이지에서 상담사 추천 페이지 (아이의 증상으로 추천  상담사 조회 ) 
//	@GetMapping("/{child_id}")
//	public RecommendTherapistResponse[] childRecommend(@PathVariable("child_id") String child_id) {
//		int childExp_no = BETService.getChildExp(child_id); 
//		RecommendTherapistResponse[] recommedTheraArr = BETService.recommendTherapistList(childExp_no);
//		return recommedTheraArr;
//		
//	}
}
