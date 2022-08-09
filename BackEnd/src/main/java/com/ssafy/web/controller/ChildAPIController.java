package com.ssafy.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/child")
public class ChildAPIController {

	@GetMapping
	public ResponseEntity<?> getChildId(){
		return null;
		
	}
}
