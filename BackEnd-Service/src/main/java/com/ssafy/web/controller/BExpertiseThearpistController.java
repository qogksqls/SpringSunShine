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
@RequestMapping("/therapist/recommed")
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
	
	//아동관리페이지에서 상담사 추천 페이지 (아이의 증상으로 추천  상담사 조회 ) 
	@GetMapping("/{parent_id}/{child_name}")
	public RecommendTherapistResponse[] childRecommend(@PathVariable("parent_id") String parent_id,
			@PathVariable("child_name") String child_name) {
		int childExp_no = BETService.getChildExp(parent_id, child_name); 
		RecommendTherapistResponse[] recommedTheraArr = BETService.recommendTherapistList(childExp_no);
		return recommedTheraArr;
		
	}
}
