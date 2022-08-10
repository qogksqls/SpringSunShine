package com.ssafy.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BExpertiseTherapistMapping;
import com.ssafy.web.service.BExpertiseTherapistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/therapist/recommed")
public class BExpertiseThearpistController {
	private final BExpertiseTherapistService BETService;
	
	@GetMapping("/{expertise_no}")
	public ResponseEntity<?> recommed(@PathVariable int expertise_no){
		BETService.recommendTherapistList(expertise_no);
		
		return new ResponseEntity<String>("ss",HttpStatus.OK);
	}
}
