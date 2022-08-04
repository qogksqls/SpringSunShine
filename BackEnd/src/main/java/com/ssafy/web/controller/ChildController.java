package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.child.ChildRegisterRequest;
import com.ssafy.web.service.child.ChildService;

@RestController
@RequestMapping("/child")
public class ChildController {

	@Autowired
	ChildService childService;

	@PostMapping
	public ResponseEntity<?> childRegist(@RequestBody ChildRegisterRequest childInfo) {
		childService.childRegist(childInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

	@DeleteMapping("/{child_id}")
	public ResponseEntity<?> childDelete(@PathVariable(value="child_id") String childId) throws Exception {
		childService.childDelete(childId);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
}
