package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.child.ChildRegisterRequest;
import com.ssafy.web.service.child.ChildService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "아동 관리 API", tags = { "Child" })
@RequestMapping("/child")
public class ChildController {

	@Autowired
	ChildService childService;

	/** 아동 등록 */
	@PostMapping("/register")
	@ApiOperation(value = "아동 등록")
	public ResponseEntity<?> childRegist(@RequestBody ChildRegisterRequest childInfo) {
		childService.childRegist(childInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

	/** 아동 정보 조회 */
	@GetMapping("/{parent_id}")
	public ResponseEntity<?> getChildList() {
		return null;
	}

	/** 난수화된 아동 아이디 반환 API */
	@GetMapping("/{parent_id}/{child_name}")
	@ApiOperation(value = "아동 아이디 난수값 반환")
	public String getChildId(@PathVariable(value = "parent_id") String parentId,
			@PathVariable(value = "child_name") String childName) {

		System.out.println(parentId);
		System.out.println(childName);
		
		String childId = childService.getChildId(parentId, childName);
		return childId;
	}

}