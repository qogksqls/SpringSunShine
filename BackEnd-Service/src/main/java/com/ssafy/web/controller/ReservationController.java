package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.Reservation;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.ReservRequest;
import com.ssafy.web.service.ReservService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "상담 예약 API", tags = {"Reservation"})
public class ReservationController {

	@Autowired
	ReservService reservService;

	@PostMapping("/reserv-parent")
	@ApiOperation(value = "상담 예약")
	public ResponseEntity<?> reservRegist(@RequestBody ReservRequest reservInfo) {
		reservService.reservRegist(reservInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));

	}

	@GetMapping("/resev-therapist/{thera_id}")
	public ResponseEntity<List<Reservation>> getReservation(@PathVariable(value = "thera_id") String theraId) {
		return new ResponseEntity<List<Reservation>>(reservService.getReservByThera(theraId), HttpStatus.OK);
	}

}
