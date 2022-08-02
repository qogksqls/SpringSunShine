package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.child.ChildRegisterRequest;
import com.ssafy.web.service.child.ChildRegisterService;

@RestController
@RequestMapping("/child")
public class ChildController {

	@Autowired
	ChildRegisterService childService;

	@PostMapping("/{id}")
	public ResponseEntity<?> childRegist(@RequestBody ChildRegisterRequest childInfo, Parent parent) {
		childService.childRegist(childInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

}
