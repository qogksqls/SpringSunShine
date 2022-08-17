package com.ssafy.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.dto.ChildData;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ChildReservResponse;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;
import com.ssafy.web.service.ChildService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "아동 관리 API", tags = { "Child" })
@RequestMapping("/child")
public class ChildController {

	@Autowired
	ChildService childService;

	/** 아동 등록 */
	@PostMapping("/register")
	@ApiOperation(value = "아동 등록")
	public ResponseEntity<?> childRegist(@RequestPart(required = false) MultipartFile profile,
			@RequestPart @ApiParam(value = "아동 등록 시 요청 정보", required = true) ChildRegisterRequest childInfo) {
		childService.childRegist(profile, childInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

	/** 아동 목록 조회 */
	@GetMapping("/{parent_id}")
	@ApiOperation(value = "아동 목록 조회")
	public List<ChildResponse> getChildList(@PathVariable(value = "parent_id") String parentId) throws IOException {
		return childService.getChildList(parentId);
	}

	/** 상담사 -> 예약한 아동 정보 조회 */
	@GetMapping("/reserv-therapist-child/{child_id}")
	public ChildReservResponse getChildInfo(@PathVariable("child_id") String childId) throws IOException {
		System.out.println("예약한 아동 정보 조회");
		ChildReservResponse childInfo = childService.getChildInfo(childId);
		System.out.println(childInfo.toString());
		return childInfo;
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
	public ChildData getChildData(@PathVariable String child_id) {
		return childService.getChildData(child_id);

	}

	/** 아동 문진표 surveryFlag 1로 수정 */
	@PutMapping("/surveyFlag/{child_id}")
	public String surveyFlag(@PathVariable("child_id") String child_id) {
		int res = childService.surveyFlag(child_id);
		if (res == 0)
			return "fail";
		else
			return "success";

	}

}