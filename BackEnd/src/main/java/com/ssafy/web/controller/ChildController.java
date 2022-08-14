package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.dto.ChildData;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;
import com.ssafy.web.service.ChildService;

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

	/** 아동 목록 조회 */
	@GetMapping("/{parent_id}")
	@ApiOperation(value = "아동 목록 조회")
	public List<ChildResponse> getChildList(@PathVariable(value = "parent_id") String parentId) {
		return childService.getChildList(parentId);
	}

	/** 난수화된 아동 아이디 반환 API */
	@GetMapping("/{parent_id}/{child_name}")
	@ApiOperation(value = "아동 아이디 난수값 반환 API")
	public String getChildId(@PathVariable(value = "parent_id") String parentId,
			@PathVariable(value = "child_name") String childName) {

		System.out.println(parentId);
		System.out.println(childName);

		String childId = childService.getChildId(parentId, childName);
		return childId;
	}

	/** 아동 나이, 성별 조회 : 문진표 진단위한 데이터 */
	@GetMapping("/getchild/{child_id}")
	public ChildData getChildData(@PathVariable String child_id){
		return childService.getChildData(child_id);
	
	}
}