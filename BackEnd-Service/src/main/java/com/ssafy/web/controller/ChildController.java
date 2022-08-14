package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;
import com.ssafy.web.service.ChildManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "아동 관리 API", tags = {"Child"})
@RequestMapping("/child")
public class ChildController {
	
	@Autowired
	ChildManagementService childManagementService;
	
	/** 아동 목록 조회 */
	@GetMapping("/{parent_id}")
	@ApiOperation(value = "아동 목록 조회")
	public List<ChildResponse> getChildList(@PathVariable(value = "parent_id") String parentId){
		return childManagementService.getChildList(parentId);
	}
	
	/** 아동 등록 */
	@PostMapping("/register")
	@ApiOperation(value = "아동 등록")
	public ResponseEntity<?> childRegist(@RequestBody ChildRegisterRequest childInfo) {
		childManagementService.childRegist(childInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

}
