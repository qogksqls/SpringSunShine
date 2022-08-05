package com.ssafy.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.request.EmailRequest;
import com.ssafy.web.service.MailService;
import com.ssafy.web.service.UserService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest email) throws Exception{
		String myemail = email.getEmail();
		System.out.println("메일 보낼 이메일 : "+myemail);
		String re ="fail";
		
		//이메일 중복 검사 하기 
		int isEmail = userService.checkEmail(myemail);
		if(isEmail == 0) {
			return new ResponseEntity<String>(re, HttpStatus.OK);
		}
		
		try{
			re = mailService.sendSimpleMessage(myemail);
		}catch(Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>(re, HttpStatus.OK);
	}
	
//	@PostMapping("/verify")
//	public int verifyCode(@RequestBody Map<String, String> codedate) {
//		System.out.println("넘어온 인증번호 : "+codedate.get("code"));
//		int result=0;
//		if(MailService.ssspass.equals(codedate.get("code"))){
//			result = 1;
//		}
//		return result;
//	}
}
