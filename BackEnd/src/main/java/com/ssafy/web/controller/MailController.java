package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	MailService mailService;
	
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody String email) throws Exception{
		System.out.println(email);
		mailService.sendMail(email);
		
		return new ResponseEntity<String>("sendSuccess", HttpStatus.OK);
	}
}
