package com.ssafy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cotroller {
	
	@GetMapping
	public String s() {
		return "sss";
	}
	
}
