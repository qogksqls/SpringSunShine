package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ChildReservResponse;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;
import com.ssafy.web.service.ChildManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "아동 관리 API", tags = { "Child" })
@RequestMapping("/child")
public class ChildController {

	@Autowired
	ChildManagementService childManagementService;

	/** 아동 목록 조회 */
	@Cacheable(value = "childList", key = "#parentId", cacheManager = "cacheManager")
	@GetMapping("/{parent_id}")
	@ApiOperation(value = "아동 목록 조회")
	public List<ChildResponse> getChildList(@PathVariable(value = "parent_id") String parentId) {
		return childManagementService.getChildList(parentId);
	}

	/** 아동 등록 */
	@Caching(evict= {
			@CacheEvict(value="childList", allEntries=true),
			@CacheEvict(value="reservChildInfo", allEntries=true)
	})
	@PostMapping("/register")
	@ApiOperation(value = "아동 등록")
	public ResponseEntity<?> childRegist(@RequestPart(required = false) MultipartFile profile,
			@RequestPart @ApiParam(value = "아동 등록 시 요청 정보", required = true) ChildRegisterRequest childInfo) {
		childManagementService.childRegist(profile, childInfo);
		System.out.println("아동 등록 service-api");
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

	/** 상담사 -> 예약한 아동 정보 조회 */
	@Cacheable(value = "reservChildInfo", key = "#childId", cacheManager = "cacheManager")
	@GetMapping("/reserv-therapist/{child_id}")
	public ChildReservResponse getChildInfo(@PathVariable("child_id") String childId) {
		return childManagementService.getChildInfo(childId);
	}

}
