package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ParentReservResponse;
import com.ssafy.web.model.response.TheraReservResponse;
import com.ssafy.web.request.ReservRequest;
import com.ssafy.web.service.ReservService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "상담 예약 API", tags = { "Reservation" })
public class ReservationController {

	@Autowired
	ReservService reservService;

	@PostMapping("/reserv-parent")
	@Caching(evict= {
			@CacheEvict(value="parentAllReserv", allEntries=true),
			@CacheEvict(value="parentAndChildReserv", allEntries=true),
			@CacheEvict(value="theraAllReserv", allEntries=true),
			@CacheEvict(value="theraAndChildReserv", allEntries=true)
	})
	@ApiOperation(value = "상담 예약")
	public ResponseEntity<?> reservRegist(@RequestBody ReservRequest reservInfo) {
		reservService.reservRegist(reservInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

	@Cacheable(value = "parentAllReserv", key = "#parentId", cacheManager = "cacheManager")
	@GetMapping("/reserv-parent/{parent_id}")
	@ApiOperation(value = "보호자가 예약한 전체 아동의 예약 날짜 조회")
	public List<ParentReservResponse> getParentReservation(@PathVariable(value = "parent_id") String parentId) {

		return reservService.getReservByParent(parentId);
	}

	@Cacheable(value = "parentAndChildReserv", key = "#parentId + #childId", cacheManager = "cacheManager")
	@GetMapping("/reserv-parent/{parent_id}/{child_id}")
	@ApiOperation(value = "보호자 아이디와 아동 아이디로 특정 아동의 예약 날짜 조회")
	public List<ParentReservResponse> getParentAndChildReservation(@PathVariable(value = "parent_id") String parentId,
			@PathVariable(value = "child_id") String childId) {

		return reservService.getReservByParentAndChild(parentId, childId);

	}

	@Cacheable(value = "theraAllReserv", key = "#theraId", cacheManager = "cacheManager")
	@GetMapping("/reserv-therapist/{thera_id}")
	@ApiOperation(value = "상담사에게 예약된 상담 내역 조회")
	public List<TheraReservResponse> getReservation(@PathVariable(value = "thera_id") String theraId) {

		return reservService.getReservByThera(theraId);
	}

	@Cacheable(value = "theraAndChildReserv", key = "#theraId + #childId", cacheManager = "cacheManager")
	@GetMapping("/reserv-therapist/child/{thera_id}/{child_id}")
	@ApiOperation(value = "상담사 아이디와 아동 아이디로 특정 아동의 예약 날짜 조회")
	public List<TheraReservResponse> getReservation(@PathVariable(value = "thera_id") String theraId,
			@PathVariable(value = "child_id") String childId) {

		return reservService.getReservByTheraAndChild(theraId, childId);
	}

}
