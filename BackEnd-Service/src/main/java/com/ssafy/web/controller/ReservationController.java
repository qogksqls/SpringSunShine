package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.ReservRequest;
import com.ssafy.web.service.ReservService;

@RestController
public class ReservationController {
	
	@Autowired
	ReservService reservService;
	
	@PostMapping("/reserv-parent")
	public ResponseEntity<?> reservRegist(@RequestBody ReservRequest reservInfo){
		reservService.reservRegist(reservInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

}
