package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.request.EmailRequest;
import com.ssafy.web.service.MailService;


@RestController
@RequestMapping("/mail")
/**SERVICE SERVER*/
public class MailController {
	
	@Autowired
	MailService mailService;
	
	/**회원가입 시, 이메일 인증번호 발송하기*/
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest email){
		String res = mailService.sendEmail(email);
		return new ResponseEntity<String> (res, HttpStatus.OK);
	}
}
