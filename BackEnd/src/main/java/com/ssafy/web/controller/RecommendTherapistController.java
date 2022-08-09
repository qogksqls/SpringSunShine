package com.ssafy.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.RecommendTherapistResponse;
import com.ssafy.web.service.RecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/therapist")
public class RecommendTherapistController {
	private final RecommendService recoService;
	
	@PostMapping("/recommed")
	public List<RecommendTherapistResponse> recommendTherapist(List<String> betmList){
		
		System.out.println(betmList);
		System.out.println(13213);
		List<String> blist = new ArrayList<String>();
		blist.add("pLQDOys220805");
		blist.add("po46qFg220805");
		List<RecommendTherapistResponse> list = recoService.findByUser_UserIdIn(blist);
		return list;
	}
	
	
}
