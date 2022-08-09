package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.model.response.ExpertiseResponse;
import com.ssafy.web.service.ExpertiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expertise")
public class ExpertiseController {
	private final ExpertiseService expertiseService;
	private final WebClient webClient;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> all(){
		Map<String,Object> data = new HashMap<String, Object>();
		List<ExpertiseResponse> expertiseList = expertiseService.expertiseFindAll();
		data.put("expertiseList",expertiseList);
		data.put("message","success");

		String result = webClient.get().uri("/user/checkid/42342").retrieve().bodyToMono(String.class).block();
		System.out.println(result);
		return new ResponseEntity<Map<String,Object>>(data,HttpStatus.OK);
	}
}
