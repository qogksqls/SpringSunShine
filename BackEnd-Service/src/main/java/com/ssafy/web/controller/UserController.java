package com.ssafy.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.service.BExpertiseTherapistService;
import com.ssafy.web.service.ExpertiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
	
	private final ExpertiseService experService;
	
	
	@GetMapping("/{user_id}")
	public List<Expertise> userInfo(@PathVariable("user_id") String user_id) {
		List<Expertise> list=null;
		
		if(user_id.charAt(0)=='p'){
			
			
		}
		
		/*치료사 회원정보 조회*/
		else if(user_id.charAt(0)=='t'){
			list = experService.findByTheraId(user_id);
			System.out.println("sdf");
		}
		
		return list;
	}
}
